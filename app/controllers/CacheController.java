package controllers;

import play.mvc.*;
import play.cache.*;

import javax.inject.Inject;

public class CacheController extends Controller {

    private final AsyncCacheApi asyncCache;
    private final SyncCacheApi syncCache;

    @Inject
    public CacheController(AsyncCacheApi asyncCache, SyncCacheApi syncCache) {
        this.asyncCache = asyncCache;
        this.syncCache = syncCache;
    }
}
