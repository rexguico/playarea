package controllers;

import play.mvc.*;
import play.http.HttpEntity;
import akka.util.*;
import akka.stream.javadsl.*;
import akka.actor.*;
import akka.stream.*;

import java.util.Collections;
import java.util.Optional;

public class FileController extends Controller {

    public Result index() {
        java.io.File file = new java.io.File("/tmp/scala.jpg");
        java.nio.file.Path path = file.toPath();
        Source<ByteString, ?> source = FileIO.fromPath(path);

        Optional<Long> contentLength = Optional.of(file.length());

        return new Result(
          new ResponseHeader(200, Collections.emptyMap()),
          new HttpEntity.Streamed(source, contentLength, Optional.of("image/jpeg"))
        );
    }

    public Result downloadHelper() {
        return ok(new java.io.File("/tmp/scala.jpg"), "cover.jpg");
    }

    public Result chunked(){
        Source<ByteString, ?> source = Source.<ByteString>actorRef(256, OverflowStrategy.dropNew())
            .mapMaterializedValue(sourceActor -> {
                sourceActor.tell(ByteString.fromString("kiki"), null);
                sourceActor.tell(ByteString.fromString("foo"), null);
                sourceActor.tell(ByteString.fromString("bar"), null);
                sourceActor.tell(new Status.Success(akka.NotUsed.getInstance()), null);
                return akka.NotUsed.getInstance();
            });

        return ok().chunked(source);
    }
}
