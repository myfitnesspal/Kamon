/* ===================================================
 * Copyright © 2013 the kamon project <http://kamon.io/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */
package kamon

import akka.actor._
import akka.event.Logging.Error

object Kamon {
  trait Extension extends akka.actor.Extension {
    def publishErrorMessage(system: ActorSystem, msg: String, cause: Throwable): Unit = {
      system.eventStream.publish(new Error(cause, "", classOf[Extension], msg))
    }
  }

  def apply[T <: Extension](key: ExtensionId[T])(implicit system: ActorSystem): T = key(system)
}

