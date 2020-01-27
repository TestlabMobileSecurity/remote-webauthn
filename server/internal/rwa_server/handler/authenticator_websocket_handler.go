/*
Copyright 2020 Paul Wagner

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package handler

import (
	"log"
	"net/http"
	"server/internal/rwa_server/message"

	"github.com/gorilla/mux"
)

// Handles incoming Auth-client session join
func HandleAuthenticatorWebsocket(w http.ResponseWriter, r *http.Request) {
	sessId := mux.Vars(r)["sessionId"]
	if sess, ok := sessions[sessId]; ok {
		conn, err := upgrader.Upgrade(w, r, nil)
		if err != nil {
			log.Fatal(err)
		}

		err = sess.SetAuthenticator(conn)
		if err != nil {
			terminateSession(sessId)
			return
		}

		go handleAuthenticatorSession(sessId)
	} else {
		w.WriteHeader(404)
	}
}

// Handles Auth-client session until closed
func handleAuthenticatorSession(sessId string) {
	sess := sessions[sessId]

	for {
		req, err := sess.ReadAuthenticatorRequest()
		if err != nil {
			terminateSession(sessId)
			break
		}

		switch req.Op {
		case message.ForwardResponseRequest:
			err := sess.ForwardResponse(req.Payload)
			if err == nil {
				terminateSession(sessId)
			}
		}
	}
}
