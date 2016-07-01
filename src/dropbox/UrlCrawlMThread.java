package dropbox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UrlCrawlMThread {
	
	private final List<Node> q = new LinkedList<>();
	private Set<String> visited = new HashSet<>();
	
	public List<String> allUrls(Node root) throws InterruptedException {		
		q.add(root);
		List<String> rlt = new ArrayList<>();
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		while (!isQueueEmpty() || pool.getActiveCount() > 0) {
			if (!isQueueEmpty()) {
				Node current = getNextFromQueue();
				rlt.add(current.url);
				pool.execute(new Crawl(current));
			} else {
				Thread.sleep(1000);
			}
			
		}
		pool.shutdown();
		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			
		}
		return rlt;
	}
	
	private class Crawl implements Runnable {
		private Node node;
		
		Crawl(Node node) {
			this.node = node;
		}
		@Override
		public void run() {
			for (Node child : node.getChildren()) {
				addToQueue(child);
			}
		}
		
	}
	
	private synchronized Node getNextFromQueue() {
		Node current = q.remove(0);			
		visited.add(current.url);
		return current;
	}
	
	private synchronized void addToQueue(Node node) {
		if (!visited.contains(node.url)) {
			q.add(node);
		}
	}
	
	private synchronized boolean isQueueEmpty() {
		return q.isEmpty();
	}
 	
	private static class Node {
		String url;
		List<Node> children;
		
		public List<Node> getChildren() {
			// in reality, need to make calls over the network to fetch the page. this is time consuming
			return children;
		}
 	}

}
