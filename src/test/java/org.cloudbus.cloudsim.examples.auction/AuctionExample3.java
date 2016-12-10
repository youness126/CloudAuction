package org.cloudbus.cloudsim.examples.auction;

import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.auction.auctioneer.Auctioneer;
import org.cloudbus.cloudsim.auction.bid.DatacenterBrokerBid;
import org.cloudbus.cloudsim.auction.bidder.BidderDatacenterBroker;
import org.cloudbus.cloudsim.auction.vm.ClonedVm;
import org.cloudbus.cloudsim.core.CloudSim;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * An example showing five Datacenters and just one Broker
 * The single Broker is winner and some Datacenters too, but enough resources aren't provided
 * on the Datacenters.
 * 
 * @author Youness Teimoury
 * Blog: http://youness-teimoury.blogspot.com/
 * Email: youness126@gmail.com
 * Created on 2011/9/9
 */
public class AuctionExample3 {

	/** The cloudlet list. */
	private static List<Cloudlet> cloudletList;

	/** The vmlist. */
	private static List<Vm> vmlist;

	private static List<Cloudlet> createCloudlet(int userId, int cloudlets){
		// Creates a container to store Cloudlets
		LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();

		//cloudlet parameters
		long length = 4000;
		long fileSize = 300;
		long outputSize = 300;
		int pesNumber = 1;
		UtilizationModel utilizationModel = new UtilizationModelFull();

		Cloudlet[] cloudlet = new Cloudlet[cloudlets];

		for(int i=0;i<cloudlets;i++){
			cloudlet[i] = new Cloudlet(i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
			// setting the owner of these Cloudlets
			cloudlet[i].setUserId(userId);
			list.add(cloudlet[i]);
		}

		return list;
	}

	@Test
	public void test() {
		Log.printLine("Starting AuctionExample3...");

		try {
			// First step: Initialize the CloudSim package. It should be called
			// before creating any entities.
			int num_user = 1;   // number of grid users
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = false;  // mean trace events

			// Initialize the CloudSim library
			CloudSim.init(num_user, calendar, trace_flag);
			Auctioneer.initAuctioneer();
			
			// Second step: Create Datacenters
			//Datacenters are the resource providers in CloudSim. We need at list one of them to run a CloudSim simulation
			Datacenter datacenter1 = BidderDatacenterFactory4.createDatacenter1();
			Datacenter datacenter2 = BidderDatacenterFactory4.createDatacenter2();
			Datacenter datacenter3 = BidderDatacenterFactory4.createDatacenter3();
			Datacenter datacenter4 = BidderDatacenterFactory4.createDatacenter4();
			Datacenter datacenter5 = BidderDatacenterFactory4.createDatacenter5();
			

			//Third step: Create Broker
			BidderDatacenterBroker broker = createBroker();
			int brokerId = broker.getId();

			//Fourth step: Create VMs and Cloudlets and send them to broker
			vmlist = createVM(brokerId,4); //creating 4 vms

			DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.03, BidderBrokerFactory4.FIXED_DURATION);
			for (Vm vm : vmlist) {
				brokerBid.addVM(vm, 1);
			}
			broker.submitBid(brokerBid);
			
			cloudletList = createCloudlet(brokerId,10); // creating 10 cloudlets

			broker.submitCloudletList(cloudletList);

			// Fifth step: Starts the simulation
			CloudSim.startSimulation();

			// Final step: Print results when simulation is over
			List<Cloudlet> newList = broker.getCloudletReceivedList();

			CloudSim.stopSimulation();

			printCloudletList(newList);

			//Print the debt of each user to each datacenter
			datacenter1.printDebts();
			datacenter2.printDebts();
			datacenter3.printDebts();
			datacenter4.printDebts();
			datacenter5.printDebts();
			

			Log.printLine("AuctionExample3 finished!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log.printLine("Unwanted errors happen");
		}
	}

	//We strongly encourage users to develop their own broker policies, to submit vms and cloudlets according
	//to the specific rules of the simulated scenario
	private static BidderDatacenterBroker createBroker(){
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return broker;
	}
	
	public static List<Vm> createVM(int userId, int vms) {

		//Creates a container to store VMs. This list is passed to the broker later
		LinkedList<Vm> list = new LinkedList<Vm>();

		//VM Parameters
		long size = 10000; //image size (MB)
		int ram = 250; //vm memory (MB)
		int mips = 250;
		long bw = 1000;
		int pesNumber = 1; //number of cpus
		String vmm = "Xen"; //VMM name

		//create VMs
		Vm[] vm = new Vm[vms];

		for(int i=0;i<vms;i++){
			vm[i] = new Vm(ClonedVm.getID(), userId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
			//for creating a VM with a space shared scheduling policy for cloudlets:
			//vm[i] = Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
			list.add(vm[i]);
		}
		return list;
	}


	/**
	 * Prints the Cloudlet objects
	 * @param list  list of Cloudlets
	 */
	private static void printCloudletList(List<Cloudlet> list) {
		int size = list.size();
		Cloudlet cloudlet;

		String indent = "    ";
		Log.printLine();
		Log.printLine("========== OUTPUT ==========");
		Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
				"Data center ID" + indent + "VM ID" + indent + indent + "Time" + indent + "Start Time" + indent + "Finish Time");

		DecimalFormat dft = new DecimalFormat("###.##");
		for (int i = 0; i < size; i++) {
			cloudlet = list.get(i);
			Log.print(indent + cloudlet.getCloudletId() + indent + indent);

			if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS){
				Log.print("SUCCESS");

				Log.printLine( indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId() +
						indent + indent + indent + dft.format(cloudlet.getActualCPUTime()) +
						indent + indent + dft.format(cloudlet.getExecStartTime())+ indent + indent + indent + dft.format(cloudlet.getFinishTime()));
			}
		}

	}
}