package io.github.shirohoo.openfeign.client;

import io.github.shirohoo.openfeign.config.CustomErrorDecoder;
import io.github.shirohoo.openfeign.config.OpenFeignConfig;
import io.github.shirohoo.openfeign.model.QueryParams;
import io.github.shirohoo.openfeign.model.form.Album;
import io.github.shirohoo.openfeign.model.form.Comment;
import io.github.shirohoo.openfeign.model.form.Photo;
import io.github.shirohoo.openfeign.model.form.Post;
import io.github.shirohoo.openfeign.model.form.Todo;
import io.github.shirohoo.openfeign.model.form.User;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "jsonPlaceHolder",
    url = "${feign.client.url.jsonPlaceHolder}",
    configuration = {
        OpenFeignConfig.class,
        CustomErrorDecoder.class
    }
)
public interface JsonPlaceHolderClient {
    @GetMapping("/posts")
    List<Post> getPosts();

    @GetMapping("/comments")
    List<Comment> getComment();

    @GetMapping("/albums")
    List<Album> getAlbums();

    @GetMapping("/photos")
    List<Photo> getPhotos();

    @GetMapping("/todos")
    List<Todo> getTodos();

    @GetMapping("/users")
    List<User> getUsers();

    @GetMapping("/users")
    List<User> getUsersWithQueryParamsBasic(@RequestParam("param1") String param1, @RequestParam("param2") String param2);

    @GetMapping("/users")
    List<User> getUsersWithQueryParams(@SpringQueryMap QueryParams queryParams);
}
