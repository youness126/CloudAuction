package org.cloudbus.cloudsim.examples.auction;

import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.auction.bid.DatacenterBrokerBid;
import org.cloudbus.cloudsim.auction.bidder.BidderDatacenterBroker;
import org.cloudbus.cloudsim.auction.vm.ClonedVm;

import java.util.LinkedList;
import java.util.List;

/**
 * A factory to create different kinds of bidder brokers
 * 
 * @author Youness Teimoury
 * Blog: http://youness-teimoury.blogspot.com/
 * Email: youness126@gmail.com
 * Created on 2011/9/16
 */

public class BidderBrokerFactory5 {

    public static BidderDatacenterBroker createBroker1() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_1");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0308, 20);
		
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
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.015, 22);

		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				500, 1, 512, 300, 4000, vmm, new CloudletSchedulerTimeShared()), 3);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets

		return broker;
	}

	public static BidderDatacenterBroker createBroker3() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_3");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.031866667, 26);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				240, 1, 256, 130, 1700, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				400, 1, 512, 120, 3000, vmm, new CloudletSchedulerTimeShared()), 2);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				500, 1, 1024, 500, 15000, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker4() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_4");
		} catch (Exception e) {
			e.printStackTrace();	
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0134, 25);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				500, 1, 512, 200, 6500, vmm, new CloudletSchedulerTimeShared()), 2);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				1000, 1, 2048, 250, 9000, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}
	
	public static BidderDatacenterBroker createBroker5() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_5");
		} catch (Exception e) {
			e.printStackTrace();	
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0263, 30);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				250, 1, 512, 150, 2500, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				400, 1, 512, 450, 10000, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				500, 1, 1024, 500, 15000, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				1000, 1, 2048, 8500, 30000, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker6() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_6");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.012884848, 23);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				330, 1, 512, 200, 7000, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				1000, 1, 1024, 500, 30000, vmm, new CloudletSchedulerTimeShared()), 2);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker7() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_7");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.042272727, 19);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				220, 1, 256, 100, 2000, vmm, new CloudletSchedulerTimeShared()), 3);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker8() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0224, 28);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				250, 1, 256, 120, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				400, 1, 1024, 350, 15000, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				1000, 1, 512, 400, 3000, vmm, new CloudletSchedulerTimeShared()), 2);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker9() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_9");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.015555556, 15);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				240, 1, 256, 100, 1700, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				900, 1, 512, 400, 3500, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker10() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_10");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.047182609, 34);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				230, 1, 256, 300, 5000, vmm, new CloudletSchedulerTimeShared()), 2);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				500, 1, 1024, 500, 4000, vmm, new CloudletSchedulerTimeShared()), 2);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				1000, 1, 512, 450, 3000, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker11() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_11");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.035914286, 60);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				350, 1, 512, 250, 7000, vmm, new CloudletSchedulerTimeShared()), 3);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				500, 1, 256, 250, 1500, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				1000, 1, 2048, 500, 7500, vmm, new CloudletSchedulerTimeShared()), 2);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//		mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker12() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_12");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.035966667, 38);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				300, 1, 512, 180, 4000, vmm, new CloudletSchedulerTimeShared()), 2);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				400, 1, 512, 140, 3000, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				500, 1, 256, 300, 1500, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				1000, 1, 512, 400, 3500, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker13() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_13");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.044333333, 55);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				240, 1, 256, 100, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				400, 1, 512, 256, 3600, vmm, new CloudletSchedulerTimeShared()), 4);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				1000, 1, 512, 450, 3800, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker14() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_14");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.046112821, 50);
		
		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				300, 1, 256, 100, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				390, 1, 512, 350, 9000, vmm, new CloudletSchedulerTimeShared()), 3);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
				500, 1, 256, 280, 1900, vmm, new CloudletSchedulerTimeShared()), 2);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId, 
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets
		
		return broker;
	}

	public static BidderDatacenterBroker createBroker15() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_15");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0338, 36);

		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				400, 1, 256, 100, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				500, 1, 512, 350, 9000, vmm, new CloudletSchedulerTimeShared()), 3);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				1000, 1, 256, 280, 1900, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets

		return broker;
	}

	public static BidderDatacenterBroker createBroker16() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_16");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0357333333, 33);

		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				300, 1, 256, 100, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				400, 1, 512, 350, 9000, vmm, new CloudletSchedulerTimeShared()), 2);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				500, 1, 256, 280, 1900, vmm, new CloudletSchedulerTimeShared()), 2);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets

		return broker;
	}

	public static BidderDatacenterBroker createBroker17() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_17");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0201282051, 14);

		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				300, 1, 256, 100, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				390, 1, 512, 350, 9000, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				500, 1, 256, 280, 1900, vmm, new CloudletSchedulerTimeShared()), 2);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets

		return broker;
	}

	public static BidderDatacenterBroker createBroker18() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_18");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0233487179, 24);

		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				300, 1, 256, 100, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				390, 1, 512, 350, 9000, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				500, 1, 256, 280, 1900, vmm, new CloudletSchedulerTimeShared()), 1);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets

		return broker;
	}

	public static BidderDatacenterBroker createBroker19() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_19");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0169, 27);

		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				500, 1, 256, 100, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				1000, 1, 512, 350, 9000, vmm, new CloudletSchedulerTimeShared()), 3);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				500, 1, 256, 280, 1900, vmm, new CloudletSchedulerTimeShared()), 2);
//		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
//				mips, 1, ram, bw, size, vmm, new CloudletSchedulerTimeShared()), 3);

		broker.submitBid(brokerBid);
		broker.submitCloudletList(createCloudlet(broker.getId(),10)); // creating and submitting 10 cloudlets

		return broker;
	}

	public static BidderDatacenterBroker createBroker20() {
		BidderDatacenterBroker broker = null;
		try {
			broker = new BidderDatacenterBroker("Broker_20");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//Create VMs and Cloudlets and send them to broker
		DatacenterBrokerBid brokerBid = new DatacenterBrokerBid(broker.getId(), 0.0533384615, 45);

		int userId = broker.getId();
		String vmm = "Xen"; //VMM name

		//for creating a VM with a space shared scheduling policy for cloudlets:
		//Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				300, 1, 256, 100, 1800, vmm, new CloudletSchedulerTimeShared()), 1);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				390, 1, 512, 350, 9000, vmm, new CloudletSchedulerTimeShared()), 4);
		brokerBid.addVM(new Vm(ClonedVm.getID(), userId,
				500, 1, 256, 280, 1900, vmm, new CloudletSchedulerTimeShared()), 1);
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
			cloudlet[i] = new Cloudlet(CLOUDLET_ID++, length, pesNumber, fileSize,
                    outputSize, utilizationModel, utilizationModel, utilizationModel);
			// setting the owner of these Cloudlets
			cloudlet[i].setUserId(userId);
			list.add(cloudlet[i]);
		}

		return list;
	}

}