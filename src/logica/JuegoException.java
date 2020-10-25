package logica;

public class JuegoException extends Exception {
	private static final long serialVersionUID = -7556832857703484707L;

	public JuegoException() {
		super();
	}

	public JuegoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public JuegoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public JuegoException(String arg0) {
		super(arg0);
	}

	public JuegoException(Throwable arg0) {
		super(arg0);
	}

}
