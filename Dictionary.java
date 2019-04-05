import java.util.*;

public interface Dictionary extends SimpleContainer {

  public static final Object NO_SUCH_KEY = new Object();

  public void insertItem(Object k, Object e) throws InvalidKeyException, InvalidArgumentException;

  public Object remove (Object k) throws InvalidKeyException, InvalidArgumentException;

  public Object findElement (Object k) throws InvalidKeyException, InvalidArgumentException;

  public Iterator findAllElements (Object k) throws InvalidKeyException, InvalidArgumentException;

  public Iterator removeAll (Object k) throws InvalidKeyException, InvalidArgumentException;

  public Iterator keys();

  public Iterator elements();

}
