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
	"server/internal/rwa_server/session"

	"github.com/gorilla/websocket"
)

// Shared session storage
var sessions = make(map[string]session.Session)

// Common HTTP -> WS upgrade logic
var upgrader = websocket.Upgrader{
	CheckOrigin: func(_ *http.Request) bool {
		return true
	},
}

// Terminates both connections and removes session from map
func terminateSession(sessId string) {
	// Only terminate if session still exists
	if sess, ok := sessions[sessId]; ok {
		sess.Terminate()
		delete(sessions, sessId)
	}
}
