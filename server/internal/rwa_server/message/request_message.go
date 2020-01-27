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

package message

type RequestOp string

// Represents the request message opcode
const (
	ForwardRequestRequest  RequestOp = "forward_request"
	ForwardResponseRequest RequestOp = "forward_response"
)

// Represents a request message
type Request struct {
	Op      RequestOp `json:"op"`
	Payload string    `json:"payload"`
}
