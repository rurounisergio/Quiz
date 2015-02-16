package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import gui.*;
import server.*;


public class SetUpClient {
	private QuizRemoteInterface remoteServerObject; //the remote server object in the registry
	
	public void launch(){
		//First the SetUpClient makes a connection with the remote QuizServer object on the registry
		connectToServer();
		NamingQuizGui questionNameGui = new NamingQuizGui(this);
		questionNameGui.launch();
		while (!questionNameGui.getHasUserEnteredName()){
			
		}
		System.out.println(questionNameGui.getUserEnteredQuizName());
	}
	
	public boolean connectToServer(){
		try {
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			remoteServerObject = (QuizRemoteInterface) reg.lookup("quizServer");
			System.out.println(remoteServerObject.createQuizId());
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return false;
	}

}
