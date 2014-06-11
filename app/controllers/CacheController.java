package controllers;

import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;

public class CacheController extends Controller {

	public static Result invalidate() {
		String key = request().getQueryString("key");
		Cache.remove(key);
		return ok();
	}
	
}
