// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/aashvin.relwani/Documents/GitHub/play-template/conf/routes
// @DATE:Tue Sep 12 17:19:01 BST 2023


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
