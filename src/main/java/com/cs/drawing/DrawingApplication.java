package com.cs.drawing;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.cs.drawing.command.CommandProcessor;
import com.cs.drawing.config.SpringConfig;
import com.cs.drawing.exception.ExitApplicationException;

@Service
public class DrawingApplication {

	@Autowired
	CommandProcessor commandProcessor;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		applicationContext.getBean(DrawingApplication.class).start();
	}

	public void start() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			while (true) {
				System.out.println();
				System.out.println("Press 'h' for help");
				System.out.print("enter command: ");
				String command = scanner.nextLine();
				try {
					String retval = commandProcessor.execute(command);
					System.out.println(retval);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} catch (ExitApplicationException e) {
					System.out.println(e.getMessage());
					break;
				}
			}

		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}
