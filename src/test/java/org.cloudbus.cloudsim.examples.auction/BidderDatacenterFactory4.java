package org.cloudbus.cloudsim.examples.auction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.auction.bid.DatacenterBid;
import org.cloudbus.cloudsim.auction.bidder.BidderDatacenter;
import org.cloudbus.cloudsim.auction.vm.DatacenterAbstractVm;
import org.cloudbus.cloudsim.auction.vm.VmCharacteristics;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 * A factory to create different kinds of bidder datacenters
 * 
 * @author Youness Teimoury
 * Blog: http://youness-teimoury.blogspot.com/
 * Email: youness126@gmail.com
 * Created on 2011/9/9
 */
public class BidderDatacenterFactory4 {
	
	public static BidderDatacenter createDatacenter1(){
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

	public static BidderDatacenter createDatacenter2(){
		String datacenterName = "Datacenter_2";

		String arch = "x86";   					// system architecture
		String os = "Linux";      				    // operating system
		String vmm = "Xen";
		double time_zone = 10.0;          // time zone this resource located

		// Here are the steps needed to create a PowerDatacenter:
		// 1. We need to create a list to store one or more
		//    Machines
		List<Host> hostList = new ArrayList<Host>();

		int mips = 3450;
		// 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
		//    create a list to store these PEs before creating a Machine.
		List<Pe> peList1 = new ArrayList<Pe>();

		// 3. Create PEs and add these into the list.
		//for a quad-core machine, a list of 4 PEs is required:
		peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

		//4. Create Hosts with its id and list of PEs and add them to the list of machines
		int hostId=0;
		int ram = 6144; //host memory (MB)
		long storage = 84000; //host storage
		int bw = 2650;

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

		bid.addVM(new DatacenterAbstractVm(300, 1,512, 200, 4000,
				new VmCharacteristics(arch, os, vmm, time_zone, 2.9, 0.06, 0.04, 0.12)), 	3);
		bid.addVM(new DatacenterAbstractVm(350, 1, 512, 250, 8000,
				new VmCharacteristics(arch, os, vmm, time_zone, 2.72, 0.06,	0.03, 0.1)),	5);
		bid.addVM(new DatacenterAbstractVm(400, 1, 1024, 400, 16000,
				new VmCharacteristics(arch, os, vmm, time_zone, 2.4, 0.04,	0.02, 0.18)),	2);

		datacenter.submitBid(bid);

		return datacenter;
	}

	public static BidderDatacenter createDatacenter3() {
		String datacenterName = "Datacenter_3";
		
		String arch = "x86";   					// system architecture
		String os = "Linux";      				    // operating system
		String vmm = "Xen";
		double time_zone = 10.0;          // time zone this resource located

		// Here are the steps needed to create a PowerDatacenter:
		// 1. We need to create a list to store one or more
		//    Machines
		List<Host> hostList = new ArrayList<Host>();

		int mips = 4700;
		// 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
		//    create a list to store these PEs before creating a Machine.
		List<Pe> peList1 = new ArrayList<Pe>();

		// 3. Create PEs and add these into the list.
		//for a quad-core machine, a list of 4 PEs is required:
		peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

		//4. Create Hosts with its id and list of PEs and add them to the list of machines
		int hostId=0;
		int ram = 7168; //host memory (MB)
		long storage = 48000; //host storage
		int bw = 1750;

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
		
		bid.addVM(new DatacenterAbstractVm(250, 1,256, 100, 2000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 3, 0.03, 0.03, 0.15)), 	6);
		bid.addVM(new DatacenterAbstractVm(400, 1, 512, 150, 4000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 2.75, 0.03, 0.02, 0.12)),	3);
		bid.addVM(new DatacenterAbstractVm(500, 1, 1024, 200, 7000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 2.5, 0.02,	0.01, 0.1)),	2);
		bid.addVM(new DatacenterAbstractVm(1000, 1, 2048, 300, 10000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 2, 0.02, 0.01, 0.05)), 1);

		datacenter.submitBid(bid);

		return datacenter;
	}

	public static BidderDatacenter createDatacenter4() {
		String datacenterName = "Datacenter_4";
		
		String arch = "x86";   					// system architecture
		String os = "Linux";      				    // operating system
		String vmm = "Xen";
		double time_zone = 10.0;          // time zone this resource located

		// Here are the steps needed to create a PowerDatacenter:
		// 1. We need to create a list to store one or more
		//    Machines
		List<Host> hostList = new ArrayList<Host>();

		int mips = 7500;
		// 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
		//    create a list to store these PEs before creating a Machine.
		List<Pe> peList1 = new ArrayList<Pe>();

		// 3. Create PEs and add these into the list.
		//for a quad-core machine, a list of 4 PEs is required:
		peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

		//4. Create Hosts with its id and list of PEs and add them to the list of machines
		int hostId=0;
		int ram = 3840; //host memory (MB)
		long storage = 30000; //host storage
		int bw = 4000;

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
		
		bid.addVM(new DatacenterAbstractVm(500, 1,256, 300, 2000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 2.8, 0.03, 0.02, 0.16)), 5);
		bid.addVM(new DatacenterAbstractVm(1000, 1, 512, 500, 4000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 1.9, 0.02, 0.01, 0.11)),  5);

		datacenter.submitBid(bid);
		return datacenter;
	}
	
	public static BidderDatacenter createDatacenter5() {
		String datacenterName = "Datacenter_5";
		
		String arch = "x86";   					// system architecture
		String os = "Linux";      				    // operating system
		String vmm = "Xen";
		double time_zone = 10.0;          // time zone this resource located

		// Here are the steps needed to create a PowerDatacenter:
		// 1. We need to create a list to store one or more
		//    Machines
		List<Host> hostList = new ArrayList<Host>();

		int mips = 6100;
		// 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
		//    create a list to store these PEs before creating a Machine.
		List<Pe> peList1 = new ArrayList<Pe>();

		// 3. Create PEs and add these into the list.
		//for a quad-core machine, a list of 4 PEs is required:
		peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

		//4. Create Hosts with its id and list of PEs and add them to the list of machines
		int hostId=0;
		int ram = 10752; //host memory (MB)
		long storage = 60000; //host storage
		int bw = 4700;

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
		
		bid.addVM(new DatacenterAbstractVm(250, 1,256, 150, 2000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 3, 0.02, 0.05, 0.1)), 2);
		bid.addVM(new DatacenterAbstractVm(400, 1, 512, 300, 4000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 2.8, 0.02, 0.05, 0.1)),  4);
		bid.addVM(new DatacenterAbstractVm(500, 1, 1024, 500, 5000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 2.5, 0.01, 0.03, 0.1)),  4);
		bid.addVM(new DatacenterAbstractVm(1000, 1, 2048, 600, 10000, 
				new VmCharacteristics(arch, os, vmm, time_zone, 2, 0.01, 0.01, 0.05)),  2);

		datacenter.submitBid(bid);
		return datacenter;
	}


}
