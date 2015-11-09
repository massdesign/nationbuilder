package nationbuilder.lib.Ruby.Exceptions;

/**
 * Created by patrick on 12/17/14.
 */
public class PostRequestFailedException extends RubyException {
    public PostRequestFailedException(String message,Exception ex) {
        super(message);
    }
}
