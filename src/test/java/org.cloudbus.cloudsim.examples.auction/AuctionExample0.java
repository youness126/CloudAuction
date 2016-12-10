package org.cloudbus.cloudsim.examples.auction;

import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.auction.auctioneer.Auctioneer;
import org.cloudbus.cloudsim.auction.bid.DatacenterBid;
import org.cloudbus.cloudsim.auction.bid.DatacenterBrokerBid;
import org.cloudbus.cloudsim.auction.bidder.BidderDatacenter;
import org.cloudbus.cloudsim.auction.bidder.BidderDatacenterBroker;
import org.cloudbus.cloudsim.auction.vm.ClonedVm;
import org.cloudbus.cloudsim.auction.vm.DatacenterAbstractVm;
import org.cloudbus.cloudsim.auction.vm.VmCharacteristics;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * An example containing 2 same bidder datacenters and 2 same broker bids.
 * 
 * @author Youness Teimoury
 * Blog: http://youness-teimoury.blogspot.com/
 * Email: youness126@gmail.com
 * Created on 2013/1/14
 */

public class AuctionExample0 {

	/**
	 * Creates main() to run this example
	 */
	@Test
	public void test() {
		Log.printLine("Starting AuctionExample0...");

		try {
			// First step: Initialize the CloudSim package. It should be called
			// before creating any entities.
			int num_user = 1;   // number of grid users
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = false;  // mean trace events

			// Initialize the CloudSim library
			CloudSim.init(num_user, calendar, trace_flag);
			Auctioneer.initAuctioneer();
			
			// Second step: Create bidder datacenters
			//Datacenters are the resource providers in CloudSim. We need at list one of them to run a CloudSim simulation
			Datacenter datacenter1 = createDatacenter1();
			Datacenter datacenter2 = createDatacenter2();

			//Third step: Create bidder broker
			DatacenterBroker broker1 = createBroker1();
			DatacenterBroker broker2 = createBroker2();

			// Forth step: Starts the simulation
			CloudSim.startSimulation();

			// Final step: Print results when simulation is over
			CloudSim.stopSimulation();

			printCloudletList(broker1.getCloudletReceivedList());
			printCloudletList(broker2.getCloudletReceivedList());

			//Print the debt of each user to each datacenter
			datacenter1.printDebts();
			datacenter2.printDebts();

			Log.printLine("AuctionExample0 finished!");
		} catch (Exception e) {
			e.printStackTrace();
			Log.printLine("Unwanted errors happen");
		}
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

    static BidderDatacenter createDatacenter1(){
        String datacenterName = "Datacenter_1";

        String arch = "x86";   					// system architecture
        String os = "Linux";      				    // operating system
        String vmm = "Xen";
        double time_zone = 10.0;          // time zone this resource located

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store one or more
        //    Machines
        List<Host> hostList = new ArrayList<Host>();

        int mips = 6950;
        // 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
        //    create a list to store these PEs before creating a Machine.
        List<Pe> peList1 = new ArrayList<Pe>();

        // 3. Create PEs and add these into the list.
        //for a quad-core machine, a list of 4 PEs is required:
        peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        //4. Create Hosts with its id and list of PEs and add them to the list of machines
        int hostId=0;
        int ram = 12032; //host memory (MB)
        long storage = 260000; //host storage
        int bw = 8000;

        Host host = new Host(
                hostId,
                new RamProvisionerSimple(ram),
                new BwProvisionerSimple(bw),
                storage,
                peList1,
                new VmSchedulerTimeShared(peList1)
            );
        hostList.add(host); // This is our only machine

        hostId++;

        LinkedList<Storage> storageList = new LinkedList<Storage>();	//we are not adding SAN devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, 0, 0, 0, 0);

        // 6. Finally, we need to create a PowerDatacenter object.
        BidderDatacenter datacenter = null;
        try {
            datacenter = new BidderDatacenter(datacenterName, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DatacenterBid bid = new DatacenterBid(datacenter.getId());

        bid.addVM(new DatacenterAbstractVm(250, 1, 256, 400, 10000,
                new VmCharacteristics(arch, os, vmm, time_zone, 3, 0.05,	0.05, 0.1)), 	5);
        bid.addVM(new DatacenterAbstractVm(400, 1, 512, 400, 10000,
                new VmCharacteristics(arch, os, vmm, time_zone, 3, 0.04,	0.04, 0.1)),	3);
        bid.addVM(new DatacenterAbstractVm(500, 1, 1024, 600, 20000,
                new VmCharacteristics(arch, os, vmm, time_zone, 2.5, 0.03,	0.03, 0.08)),	3);
        bid.addVM(new DatacenterAbstractVm(1000, 1, 2048, 1000, 40000,
                new VmCharacteristics(arch, os, vmm, time_zone, 2, 0.02,	0.01, 0.05)),	3);

        datacenter.submitBid(bid);

        return datacenter;
    }

    static BidderDatacenter createDatacenter2(){
        String datacenterName = "Datacenter_2";

        String arch = "x86";   					// system architecture
        String os = "Linux";      				    // operating system
        String vmm = "Xen";
        double time_zone = 10.0;          // time zone this resource located

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store one or more
        //    Machines
        List<Host> hostList = new ArrayList<Host>();

        int mips = 6950;
        // 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
        //    create a list to store these PEs before creating a Machine.
        List<Pe> peList1 = new ArrayList<Pe>();

        // 3. Create PEs and add these into the list.
        //for a quad-core machine, a list of 4 PEs is required:
        peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        //4. Create Hosts with its id and list of PEs and add them to the list of machines
        int hostId=0;
        int ram = 12032; //host memory (MB)
        long storage = 260000; //host storage
        int bw = 8000;

        Host host = new Host(
                hostId,
                new RamProvisionerSimple(ram),
                new BwProvisionerSimple(bw),
                storage,
                peList1,
                new VmSchedulerTimeShared(peList1)
            );
        hostList.add(host); // This is our only machine

        hostId++;

        LinkedList<Storage> storageList = new LinkedList<Storage>();	//we are not adding SAN devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, 0, 0, 0, 0);

        // 6. Finally, we need to create a PowerDatacenter object.
        BidderDatacenter datacenter = null;
        try {
            datacenter = new BidderDatacenter(datacenterName, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DatacenterBid bid = new DatacenterBid(datacenter.getId());

        bid.addVM(new DatacenterAbstractVm(250, 1, 256, 400, 10000,
                new VmCharacteristics(arch, os, vmm, time_zone, 3, 0.05,	0.05, 0.1)), 	5);
        bid.addVM(new DatacenterAbstractVm(400, 1, 512, 400, 10000,
                new VmCharacteristics(arch, os, vmm, time_zone, 3, 0.04,	0.04, 0.1)),	3);
        bid.addVM(new DatacenterAbstractVm(500, 1, 1024, 600, 20000,
                new VmCharacteristics(arch, os, vmm, time_zone, 2.5, 0.03,	0.03, 0.08)),	3);
        bid.addVM(new DatacenterAbstractVm(1000, 1, 2048, 1000, 40000,
                new VmCharacteristics(arch, os, vmm, time_zone, 2, 0.02,	0.01, 0.05)),	3);

        datacenter.submitBid(bid);

        return datacenter;
    }



    public static BidderDatacenterBroker createBroker1() {
        BidderDatacenterBroker broker = null;
        try {
            broker = new BidderDatacenterBroker("Broker_1");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        //Create VMs and Cloudlets and send them to broker
        DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0308, 10);

        int userId = broker.getId();
        String vmm = "Xen"; //VMM name

        //for creating a VM with a space shared scheduling policy for cloudlets:
        //Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
        brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
                250, 1, 256, 400, 10000, vmm, new CloudletSchedulerTimeShared()), 2);
        brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
                500, 1, 256, 250, 1500, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

        broker.submitBid(brokerBid);
        broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets

        return broker;
    }
    public static BidderDatacenterBroker createBroker2() {
        BidderDatacenterBroker broker = null;
        try {
            broker = new BidderDatacenterBroker("Broker_2");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        //Create VMs and Cloudlets and send them to broker
        DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0308, 10);

        int userId = broker.getId();
        String vmm = "Xen"; //VMM name

        //for creating a VM with a space shared scheduling policy for cloudlets:
        //Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
        brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
                250, 1, 256, 400, 10000, vmm, new CloudletSchedulerTimeShared()), 2);
        brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
                500, 1, 256, 250, 1500, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

        broker.submitBid(brokerBid);
        broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets

        return broker;
    }

    /**
     * Cloudlet unique ID
     */
    private static int CLOUDLET_ID = 0;

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
            cloudlet[i] = new Cloudlet(CLOUDLET_ID++, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            // setting the owner of these Cloudlets
            cloudlet[i].setUserId(userId);
            list.add(cloudlet[i]);
        }

        return list;
    }



}