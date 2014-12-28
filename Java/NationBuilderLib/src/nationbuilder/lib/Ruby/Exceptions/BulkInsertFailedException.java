package nationbuilder.lib.Ruby.Exceptions;

/**
 * Created by patrick on 12/28/14.
 */
public class BulkInsertFailedException extends RubyException {
    public BulkInsertFailedException(String message) {
        super("Bulk insert failed at " + message);
    }
}
