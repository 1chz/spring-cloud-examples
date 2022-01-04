package io.github.shirohoo.openfeign.client;

import static org.assertj.core.api.Assertions.assertThat;
import io.github.shirohoo.openfeign.model.QueryParams;
import io.github.shirohoo.openfeign.model.form.Album;
import io.github.shirohoo.openfeign.model.form.Comment;
import io.github.shirohoo.openfeign.model.form.Photo;
import io.github.shirohoo.openfeign.model.form.Post;
import io.github.shirohoo.openfeign.model.form.Todo;
import io.github.shirohoo.openfeign.model.form.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonPlaceHolderClientTest {
    @Autowired
    JsonPlaceHolderClient client;

    @Test
    void getPosts() throws Exception {
        List<Post> posts = client.getPosts();
        assertThat(posts.size()).isEqualTo(100);
    }

    @Test
    void getComments() throws Exception {
        List<Comment> posts = client.getComment();
        assertThat(posts.size()).isEqualTo(500);
    }

    @Test
    void getAlbums() throws Exception {
        List<Album> posts = client.getAlbums();
        assertThat(posts.size()).isEqualTo(100);
    }

    @Test
    void getPhotos() throws Exception {
        List<Photo> posts = client.getPhotos();
        assertThat(posts.size()).isEqualTo(5000);
    }

    @Test
    void getTodos() throws Exception {
        List<Todo> posts = client.getTodos();
        assertThat(posts.size()).isEqualTo(200);
    }

    @Test
    void getUsers() throws Exception {
        List<User> posts = client.getUsers();
        assertThat(posts.size()).isEqualTo(10);
    }

    @Test
    void getUsersWithQueryParams() throws Exception {
        // ...given
        QueryParams queryParams = new QueryParams();
        queryParams.setParam1("param1");
        queryParams.setParam2("param2");

        // ...when
        List<User> posts = client.getUsersWithQueryParams(queryParams);

        // ...then
        assertThat(posts.size()).isEqualTo(10);
    }
}
