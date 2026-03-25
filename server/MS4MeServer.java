package server

import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class MS4MEServer {
	private ServerSocket serverSocket;
	private Socket clientSocket;

	private Queue<Event> events = new LinkedList<Event>();
	private LinkedHashMap<String, String> models;

	private BufferedWriter logger;

	private ObjectOutputStream oOut;
	private ObjectInputStream oIn;

	private AtomicModelImpl controller;

	private OperatorManager operatorManager;

	private int traceNumber;
	private int currentStep;

	public MS4MEController(AtomicModelImpl controller){
		super();
		this.controller = controller;
		this.models = new LinkedHashMap<String, String>();
		this.traceNumber = 0;
		this.currentStep = 0;
		try {
			this.logger = new BufferedWriter(new FileWriter(getModelsDirectory().getAbsolutePath() + "/txt/trace.txt"));
		} catch (Exception e) {
		}
	}

	public void start() {
		new Thread(new Runnable() {
			public void run() {
				MS4MEController.this.run();
			}
		}).start();
	}

	private void run() {
		Request request = null;
		try {
			open();
			while (true) {
				clientSocket = serverSocket.accept();
				System.out.println("Connection established");
				oOut = new ObjectOutputStream(clientSocket.getOutputStream());
				oIn = new ObjectInputStream(clientSocket.getInputStream());
				while (true) {
					try {
						request = (Request) oIn.readObject();
					} catch (EOFException e2) {
						break;
					} catch (ClassNotFoundException e) {
						System.out.println("Unexpected message from the simulator.");
					}
				}
				System.out.println("Connection disconnected");
				close();
				return;
			}

		} catch (IOException e) {
			close();
		}
	}

}