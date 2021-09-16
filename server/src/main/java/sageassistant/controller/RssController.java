package sageassistant.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;

import sageassistant.service.RssService;

@RestController
public class RssController {

	@Autowired
	RssService rssService;

	@GetMapping(path = "/Data/Rss")
	public Channel rss(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "range", required = false, defaultValue = "28") Integer range) {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		if (type.equals("")) {
			channel.setTitle(Site + " All");
		} else {
			channel.setTitle(Site + " " + type);
		}

		channel.setDescription("Alert From SageAssistant");
		channel.setLink("http://SageAssistant");
		channel.setGenerator("Rome");
		channel.setTtl(24 * 60);

		List<String> skipDays = new ArrayList<String>();
		skipDays.add("Saturday");
		skipDays.add("Sunday");

		List<Integer> skipHours = new ArrayList<Integer>();
		skipHours.add(0);
		skipHours.add(1);
		skipHours.add(2);
		skipHours.add(3);
		skipHours.add(4);
		skipHours.add(5);
		skipHours.add(6);
		skipHours.add(7);
		skipHours.add(8);
		skipHours.add(18);
		skipHours.add(19);
		skipHours.add(20);
		skipHours.add(21);
		skipHours.add(22);
		skipHours.add(23);

		channel.setSkipDays(skipDays);
		channel.setSkipHours(skipHours);

		Date postDate = new Date();
		channel.setPubDate(postDate);
		channel.setItems(Collections.singletonList(getRssItemBySite(Site, type, range)));
		return channel;
	}

	private Item getRssItemBySite(String Site, String type, Integer range) {
		Item item = new Item();
		Date postDate = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

		item.setPubDate(postDate);
		item.setAuthor(Site);
		item.setTitle("Date:" + fmt.format(postDate));
		item.setLink("http://SageAssistant/#/Todo");
		Description descr = new Description();

		String content = "";
		String pnContent = "";
		String tobeDeliveryContent = "";
		String tobeDealWithOrderLineContent = "";
		String tobePurchaseBom = "";
		String tobeReceiveContent = "";

		if (type.equals("") || type.equals("PnStatus")) {
			pnContent = rssService.checkPnUpdate(Site);
		}
		if (type.equals("") || type.equals("Delivery")) {
			tobeDeliveryContent = rssService.checkTobeDelivery(Site, range);
		}
		if (type.equals("") || type.equals("OrderLine")) {
			tobeDealWithOrderLineContent = rssService.checkTobeDealWithOrderLine(Site);
		}
		if (type.equals("") || type.equals("PurchaseBom")) {
			tobePurchaseBom = rssService.checkTobePurchaseBom(Site);
		}
		if (type.equals("") || type.equals("Receive")) {
			tobeReceiveContent = rssService.checkTobeReceive(Site, range);
		}

		if (!pnContent.equals("")) {
			content += "<h3>Pn not be ACTIVE status now:[PnStatus]</h3>";
			content += pnContent;
		}
		if (!tobeDeliveryContent.equals("")) {
			content += "<h3>The product need to be delivery in next " + range + " Days:[Delivery]</h3>";
			content += tobeDeliveryContent;
		}
		if (!tobeDealWithOrderLineContent.equals("")) {
			content += "<h3>These sales order need to be deal with:[OrderLine]</h3>";
			content += tobeDealWithOrderLineContent;
		}
		if (!tobePurchaseBom.equals("")) {
			content += "<h3>These Boms need to be puchase:[PurchaseBom]</h3>";
			content += tobePurchaseBom;
		}
		if (!tobeReceiveContent.equals("")) {
			content += "<h3>These Boms need to be receive in next " + range + " Days:[Receive]</h3>";
			content += tobeReceiveContent;
		}

		descr.setValue(content);

		item.setDescription(descr);
		return item;
	}

}
