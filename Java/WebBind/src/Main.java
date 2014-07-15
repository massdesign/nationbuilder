
/*import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
*/
import java.io.IOException;

/**
 * Created by patrick on 7/7/14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("blabla");
        /*
        Handler handler=new AbstractHandler()
        {
            public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
                    throws IOException, ServletException
            {
                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("<h1>Hello</h1>");
                ((Request)request).setHandled(true);
            }
        };

        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
        */
    }
}
