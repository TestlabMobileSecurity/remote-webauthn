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
	"net/http"
	"server/internal/rwa_server/message"
	"server/internal/rwa_server/session"
)

// Handles incoming RP-client session initialization
func HandleClientWebsocket(w http.ResponseWriter, r *http.Request) {
	conn, err := upgrader.Upgrade(w, r, nil)
	if err != nil {
		return
	}

	sessId, sess, err := session.New(conn)
	if err != nil {
		_ = conn.Close()
		return
	}
	sessions[sessId] = sess

	go handleClientSession(sessId)
}

// Handles RP-client session until closed
func handleClientSession(sessId string) {
	sess := sessions[sessId]

	for {
		req, err := sess.ReadClientRequest()
		if err != nil {
			terminateSession(sessId)
			break
		}

		switch req.Op {
		case message.ForwardRequestRequest:
			err := sess.ForwardRequest(req.Payload)
			if err != nil {
				continue
			}
		}
	}
}
