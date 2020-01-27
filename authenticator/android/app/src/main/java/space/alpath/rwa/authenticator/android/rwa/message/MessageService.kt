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

package space.alpath.rwa.authenticator.android.rwa.message

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

/**
 * Represents a Scarlet WebSocket service receiving events and sending out requests.
 */
interface MessageService {
    /**
     * Fires whenever new web socket status messages arrive.
     */
    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>

    /**
     * Fires whenever new event messages arrive.
     */
    @Receive
    fun observeEventMessages(): Flowable<EventMessage>

    /**
     * Sends request message over WebSocket tunnel to proxy.
     * @param request request to send over tunnel
     */
    @Send
    fun sendRequest(request: RequestMessage)
}