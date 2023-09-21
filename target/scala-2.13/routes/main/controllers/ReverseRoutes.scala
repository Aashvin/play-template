// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:2
package controllers {

  // @LINE:4
  class ReverseApplicationController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def read(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:11
    def create(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "api/create")
    }
  
    // @LINE:25
    def getGoogleBook(search:String, term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "library/google/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("search", search)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
    // @LINE:13
    def createFromGoogle(search:String, term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/create/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("search", search)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
    // @LINE:29
    def addBook(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "addBook/form")
    }
  
    // @LINE:4
    def viewBook(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "viewBook/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:30
    def addBookForm(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addBook/form")
    }
  
    // @LINE:21
    def updateByID(id:String, field:String, value:String): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "api/update/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("field", field)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("value", value)))
    }
  
    // @LINE:23
    def delete(id:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:17
    def findBySearch(field:String, value:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/search/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("field", field)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("value", value)))
    }
  
    // @LINE:19
    def update(id:String): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:9
    def index(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api")
    }
  
    // @LINE:27
    def getGoogleBookAsDataModel(search:String, term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "library/googletobook/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("search", search)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
  }

  // @LINE:2
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:7
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
