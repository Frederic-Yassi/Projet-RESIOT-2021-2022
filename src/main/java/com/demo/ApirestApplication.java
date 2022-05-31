package com.demo;

import com.Functions_knx.*;
import com.Functions_bdd.*;

import javax.websocket.server.PathParam;

import com.Socket.SpringWebsocketApplication;
import com.Socket.simulKnx;
import com.Socket.simulKnx2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tuwien.auto.calimero.KNXException;

import java.io.IOException;
import java.util.Collections;


@SpringBootApplication
@RestController

public class ApirestApplication {
	public static boolean work=false;
	public static boolean pause=false;
	public static boolean sense=false;
	public static KNX knx = new KNX();
	simulKnx g ;
	simulKnx2 h ;

	public static void main(String[] args) throws IOException, KNXException, InterruptedException {

		//thread principale
		SpringApplication app1 = new SpringApplication(ApirestApplication.class);
		app1.setDefaultProperties(Collections.singletonMap("server.port", "8013"));
		app1.run(args);

		SpringApplication app2 = new SpringApplication(SpringWebsocketApplication.class);
		app2.setDefaultProperties(Collections.singletonMap("server.port", "8014"));
		app2.run(args);

		new GroupMonitor().run();

		/*Mythread t = new Mythread();
		t.start();*/

		//thread knx


	}

    @GetMapping("/api")
    public String test() {
		System.out.println("Accessibilité method stop work");
		return "API est effectivement accessible !";
    }

    @GetMapping("/api/conf")
	public String Configuration() {
		try {
			db.connect();
			db.add("configuration", "");
			db.deconnect();
			return "Configuration effectué avec succes .";
		}
		catch(Exception e) {
			System.out.println(e);
			return "erreur de connection avec la Base de données";
		}

	}

	@PostMapping("/api/start")
	public void start() {
		if(sense==false){
			g = new simulKnx();
			work=true ;
			g.start();
		}
		else {
			h = new simulKnx2();
			work=true ;
			h.start();
		}



        knx.start();

		db.connect();
		db.add("start", "");
		db.deconnect();
		System.out.println("start method work");
	}

	@PostMapping("/api/stop")
	public void stop() {
		work=false;
		if(sense==false){
			g.stop();
		}
		else{
			h.stop();
		}
		db.connect();
		db.add("stop", "");
		db.deconnect();
		knx.stop();
		System.out.println("Stop method work");
	}


	@PostMapping("/api/pause")
	public void pause() {
		if(sense==false) {
			if (pause == false) {
				g.suspend();
				pause = true;
			} else {
				g.resume();
				pause = false;
			}
		}
		else{
			if (pause == false) {
				h.suspend();
				pause = true;
			} else {
				h.resume();
				pause = false;
			}
		}
		db.connect();
		db.add("pause", "");
		db.deconnect();
		knx.stop();
		System.out.println("pause method work");
	}

	@PostMapping("/api/accelerate")
	public void accelerate() {
		knx.changeSpeed(1);
		db.connect();
		db.add("accelerate", "");
		db.deconnect();
		System.out.println("Accelerate method stop work");
	}

	@PostMapping("/api/delay")
	public void delay() {
		knx.changeSpeed(-1);
		db.connect();
		db.add("delay", "");
		db.deconnect();
		System.out.println("delay method  work");
	}

	@PostMapping("/api/left")
	public void left() {
		g.stop();
		h=new simulKnx2();
		h.start();
		sense=true;
		knx.changeSense();
		db.connect();
		db.add("delay", "");
		db.deconnect();
		System.out.println("changeSense method  work");
	}

	@PostMapping("/api/right")
	public void right() {
		h.stop();
		g=new simulKnx();
		g.start();
		sense=false;
		knx.changeSense();
		db.connect();
		db.add("delay", "");
		db.deconnect();
		System.out.println("changeSense method  work");
	}

	@PostMapping("/api/changeMotif")
	public void changeMotif() {
		knx.changeMotif();
		db.connect();
		db.add("changeMotif", "");
		db.deconnect();
		System.out.println("changeMotif method  work");
	}


	@PostMapping("/api/configure")
	public void configure(@RequestBody String conf) {
		db.connect();
		db.add("configure", "");
		db.deconnect();
		System.out.println(conf);
	}

	@GetMapping("/api/state")
	public String state(@PathParam("ok") String ok) {
		db.connect();
		db.add("state", "");
		db.deconnect();
		return ok;
	}

	@GetMapping("/api/historique")
	public String getHistory() {
		db.connect();
		db.get();
		db.deconnect();
		return "ok";
	}
}