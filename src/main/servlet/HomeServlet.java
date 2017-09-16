import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by s3virge on 13.09.17.
 */

//Если urlPatterns = "/", тогда ничего не работает и по внутренним ссылкам сайта перейти не возможно,
// отсюда и невозможность подгружать CSS, JS и т.д.
@WebServlet(urlPatterns = "", name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());

        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();

        WebContext ctx = new WebContext(req, resp, getServletConfig().getServletContext(), req.getLocale());
        ctx.setVariable("today", dateFormat.format(cal.getTime()));

        // This will be prefixed with /WEB-INF/ and suffixed with .html
        templateEngine.process("home", ctx, resp.getWriter());

    }
}
