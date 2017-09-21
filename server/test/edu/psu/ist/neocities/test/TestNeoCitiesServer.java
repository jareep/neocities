package edu.psu.ist.neocities.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.psu.ist.neocities.NeoCitiesServer;
//import edu.psu.ist.neocities.value.IncidentVO;

public class TestNeoCitiesServer {

	private NeoCitiesServer server;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		server = new NeoCitiesServer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testNeoCitiesServer() {	}

	@Test
	public final void testStart() throws InterruptedException {
		
		
		/*
		server.addIncident(new IncidentVO(1, "Party at the MINDS Lab", "It's out of control", "party.gif", 1,  1000, 20000));
		server.addIncident(new IncidentVO(2, "Indecent Exposure on Beaver Alley", "Crazy Drunk Chick", "party.gif", 2,  5000, 25000));
		server.addIncident(new IncidentVO(3, "Noise Violation", "Damn kids and their rap music", "party.gif", 3, 10000, 30000));
		*/
		server.start();
		
		assertEquals("", server.getLastEventLabel());
		Thread.sleep(3500);
		assertEquals("1Party at the MINDS Lab", server.getLastEventLabel());
		Thread.sleep(1500);
		assertEquals("2Indecent Exposure on Beaver Alley", server.getLastEventLabel());
		Thread.sleep(5500);
		assertEquals("3Noise Violation", server.getLastEventLabel());
	}

	@Test
	public final void testStop() {
	}

}
