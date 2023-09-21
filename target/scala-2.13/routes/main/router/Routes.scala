// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:2
  HomeController_2: controllers.HomeController,
  // @LINE:4
  ApplicationController_0: controllers.ApplicationController,
  // @LINE:7
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    HomeController_2: controllers.HomeController,
    // @LINE:4
    ApplicationController_0: controllers.ApplicationController,
    // @LINE:7
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_2, ApplicationController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_2, ApplicationController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """viewBook/""" + "$" + """id<[^/]+>""", """controllers.ApplicationController.viewBook(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api""", """controllers.ApplicationController.index()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/create""", """controllers.ApplicationController.create()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/create/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""", """controllers.ApplicationController.createFromGoogle(search:String, term:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """id<[^/]+>""", """controllers.ApplicationController.read(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/search/""" + "$" + """field<[^/]+>/""" + "$" + """value<[^/]+>""", """controllers.ApplicationController.findBySearch(field:String, value:String)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """id<[^/]+>""", """controllers.ApplicationController.update(id:String)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/update/""" + "$" + """id<[^/]+>/""" + "$" + """field<[^/]+>/""" + "$" + """value<[^/]+>""", """controllers.ApplicationController.updateByID(id:String, field:String, value:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """id<[^/]+>""", """controllers.ApplicationController.delete(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """library/google/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""", """controllers.ApplicationController.getGoogleBook(search:String, term:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """library/googletobook/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""", """controllers.ApplicationController.getGoogleBookAsDataModel(search:String, term:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addBook/form""", """controllers.ApplicationController.addBook()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addBook/form""", """controllers.ApplicationController.addBookForm()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:2
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_2.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An viewBook controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:4
  private[this] lazy val controllers_ApplicationController_viewBook1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("viewBook/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_viewBook1_invoker = createInvoker(
    ApplicationController_0.viewBook(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "viewBook",
      Seq(classOf[String]),
      "GET",
      this.prefix + """viewBook/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_Assets_versioned2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned2_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_ApplicationController_index3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api")))
  )
  private[this] lazy val controllers_ApplicationController_index3_invoker = createInvoker(
    ApplicationController_0.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "index",
      Nil,
      "GET",
      this.prefix + """api""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_ApplicationController_create4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/create")))
  )
  private[this] lazy val controllers_ApplicationController_create4_invoker = createInvoker(
    ApplicationController_0.create(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "create",
      Nil,
      "POST",
      this.prefix + """api/create""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_ApplicationController_createFromGoogle5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/create/"), DynamicPart("search", """[^/]+""",true), StaticPart("/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_createFromGoogle5_invoker = createInvoker(
    ApplicationController_0.createFromGoogle(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "createFromGoogle",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """api/create/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_ApplicationController_read6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_read6_invoker = createInvoker(
    ApplicationController_0.read(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "read",
      Seq(classOf[String]),
      "GET",
      this.prefix + """api/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_ApplicationController_findBySearch7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/search/"), DynamicPart("field", """[^/]+""",true), StaticPart("/"), DynamicPart("value", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_findBySearch7_invoker = createInvoker(
    ApplicationController_0.findBySearch(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "findBySearch",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """api/search/""" + "$" + """field<[^/]+>/""" + "$" + """value<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_ApplicationController_update8_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_update8_invoker = createInvoker(
    ApplicationController_0.update(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "update",
      Seq(classOf[String]),
      "PUT",
      this.prefix + """api/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_ApplicationController_updateByID9_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/update/"), DynamicPart("id", """[^/]+""",true), StaticPart("/"), DynamicPart("field", """[^/]+""",true), StaticPart("/"), DynamicPart("value", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_updateByID9_invoker = createInvoker(
    ApplicationController_0.updateByID(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "updateByID",
      Seq(classOf[String], classOf[String], classOf[String]),
      "PUT",
      this.prefix + """api/update/""" + "$" + """id<[^/]+>/""" + "$" + """field<[^/]+>/""" + "$" + """value<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_ApplicationController_delete10_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_delete10_invoker = createInvoker(
    ApplicationController_0.delete(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "delete",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """api/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:25
  private[this] lazy val controllers_ApplicationController_getGoogleBook11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("library/google/"), DynamicPart("search", """[^/]+""",true), StaticPart("/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_getGoogleBook11_invoker = createInvoker(
    ApplicationController_0.getGoogleBook(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "getGoogleBook",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """library/google/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:27
  private[this] lazy val controllers_ApplicationController_getGoogleBookAsDataModel12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("library/googletobook/"), DynamicPart("search", """[^/]+""",true), StaticPart("/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_getGoogleBookAsDataModel12_invoker = createInvoker(
    ApplicationController_0.getGoogleBookAsDataModel(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "getGoogleBookAsDataModel",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """library/googletobook/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:29
  private[this] lazy val controllers_ApplicationController_addBook13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addBook/form")))
  )
  private[this] lazy val controllers_ApplicationController_addBook13_invoker = createInvoker(
    ApplicationController_0.addBook(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "addBook",
      Nil,
      "GET",
      this.prefix + """addBook/form""",
      """""",
      Seq()
    )
  )

  // @LINE:30
  private[this] lazy val controllers_ApplicationController_addBookForm14_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addBook/form")))
  )
  private[this] lazy val controllers_ApplicationController_addBookForm14_invoker = createInvoker(
    ApplicationController_0.addBookForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "addBookForm",
      Nil,
      "POST",
      this.prefix + """addBook/form""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_2.index())
      }
  
    // @LINE:4
    case controllers_ApplicationController_viewBook1_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ApplicationController_viewBook1_invoker.call(ApplicationController_0.viewBook(id))
      }
  
    // @LINE:7
    case controllers_Assets_versioned2_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned2_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:9
    case controllers_ApplicationController_index3_route(params@_) =>
      call { 
        controllers_ApplicationController_index3_invoker.call(ApplicationController_0.index())
      }
  
    // @LINE:11
    case controllers_ApplicationController_create4_route(params@_) =>
      call { 
        controllers_ApplicationController_create4_invoker.call(ApplicationController_0.create())
      }
  
    // @LINE:13
    case controllers_ApplicationController_createFromGoogle5_route(params@_) =>
      call(params.fromPath[String]("search", None), params.fromPath[String]("term", None)) { (search, term) =>
        controllers_ApplicationController_createFromGoogle5_invoker.call(ApplicationController_0.createFromGoogle(search, term))
      }
  
    // @LINE:15
    case controllers_ApplicationController_read6_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ApplicationController_read6_invoker.call(ApplicationController_0.read(id))
      }
  
    // @LINE:17
    case controllers_ApplicationController_findBySearch7_route(params@_) =>
      call(params.fromPath[String]("field", None), params.fromPath[String]("value", None)) { (field, value) =>
        controllers_ApplicationController_findBySearch7_invoker.call(ApplicationController_0.findBySearch(field, value))
      }
  
    // @LINE:19
    case controllers_ApplicationController_update8_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ApplicationController_update8_invoker.call(ApplicationController_0.update(id))
      }
  
    // @LINE:21
    case controllers_ApplicationController_updateByID9_route(params@_) =>
      call(params.fromPath[String]("id", None), params.fromPath[String]("field", None), params.fromPath[String]("value", None)) { (id, field, value) =>
        controllers_ApplicationController_updateByID9_invoker.call(ApplicationController_0.updateByID(id, field, value))
      }
  
    // @LINE:23
    case controllers_ApplicationController_delete10_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ApplicationController_delete10_invoker.call(ApplicationController_0.delete(id))
      }
  
    // @LINE:25
    case controllers_ApplicationController_getGoogleBook11_route(params@_) =>
      call(params.fromPath[String]("search", None), params.fromPath[String]("term", None)) { (search, term) =>
        controllers_ApplicationController_getGoogleBook11_invoker.call(ApplicationController_0.getGoogleBook(search, term))
      }
  
    // @LINE:27
    case controllers_ApplicationController_getGoogleBookAsDataModel12_route(params@_) =>
      call(params.fromPath[String]("search", None), params.fromPath[String]("term", None)) { (search, term) =>
        controllers_ApplicationController_getGoogleBookAsDataModel12_invoker.call(ApplicationController_0.getGoogleBookAsDataModel(search, term))
      }
  
    // @LINE:29
    case controllers_ApplicationController_addBook13_route(params@_) =>
      call { 
        controllers_ApplicationController_addBook13_invoker.call(ApplicationController_0.addBook())
      }
  
    // @LINE:30
    case controllers_ApplicationController_addBookForm14_route(params@_) =>
      call { 
        controllers_ApplicationController_addBookForm14_invoker.call(ApplicationController_0.addBookForm())
      }
  }
}
