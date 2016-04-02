	public Server(int nbP, int port) throws IOException
	{
		porNumber = port;
		nbPlayer = nbP;
		socket = new ServerSocket(port);
		clientSocket = new Socket[nbP];
		input = new BufferedReader[nbP];
		output = new PrintWriter[nbP];
		for(int i =0; i < nbP; i++)
		{
			String stat = null;
			clientSocket[i] = socket.accept();
			input[i] = new BufferedReader (new InputStreamReader(clientSocket[i].getInputStream()));
			output[i] = new PrintWriter(clientSocket[i].getOutputStream(), true);
			output[i].println("player"+i);
			System.out.println("send done");
			stat = input[i].readLine();
			System.out.println("input OK");
			System.out.println("init : "+i+" status: "+stat);
		}
	}