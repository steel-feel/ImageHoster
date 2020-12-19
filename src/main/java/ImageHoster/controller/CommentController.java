package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ImageService imageService;

    //Method to Post comments on image by particular user
    @RequestMapping(value = "image/{id}/{title}/comments", method = RequestMethod.POST)
    private String postComment(@PathVariable("id") Integer imageId, @RequestParam(name = "comment") String commentText, HttpSession session )
    {
        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");

        Comment newComment = new Comment();
        newComment.setText(commentText);
        newComment.setUser(user);
        newComment.setCreatedDate(new Date());
        newComment.setImage(image);

        commentService.addImageComment(image,newComment);

        return "redirect:/images/{id}/{title}";

    }

}
