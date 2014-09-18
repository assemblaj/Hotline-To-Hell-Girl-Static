import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
import java.io.*;

public class PageGenerator {
	public static void main(String[] args) {
	   String[][] epData;
	   WriteHTML writer;
	   String[] categories = {"Family", "School", "Arc", "Origin", 
	                          "Morbid", "Unrequited Love", "Slice of Life", 
							  "Adolescence", "Abuse", "Crime", "Neighbor",
							  "Accident", "Politics", "Work", "Friendship", "Bullying"};
	   try {
	       SAXParserFactory factory = SAXParserFactory.newInstance();
		   SAXParser saxParser = factory.newSAXParser();
		   
		   DataHandler handler = new DataHandler();
		   saxParser.parse("hell_database.xml", handler);
		   epData = handler.getEpData();
		   writer = new WriteHTML(epData);
		  
	      for(int i = 0; i< 52; i++) {		  
		     writer.makeEpisodePage(i);
		  } /*
		  for (String c : categories) 
			writer.makeCategoryPage(c);
		  for (int i=0; i < categories.length; i++)
			writer.makeCategoryPage(categories[i]);*/
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   
	}
}

class DataHandler extends DefaultHandler 
{
	private String[][] epData = new String[52][7];
	private String temp;
	private int epCount = 0;
	private int count = 0;
	private String elName;
	private StringBuffer stringBuffer;
	String result;
	public DataHandler() {
		stringBuffer = new StringBuffer();
	}
	
	public void startElement(
		String uri, String localName,
		String qName,  Attributes attributes)
		throws SAXException
	{
		//System.out.println(qName);
		
	}
		   
	public void endElement(
		String uri, String localName,
		String qName) throws SAXException 
	{
		result = stringBuffer.toString();
		stringBuffer.setLength(0);
		//System.out.println(result);
		if (qName.equalsIgnoreCase("EpID")) {
			epData[epCount][0] = result;
			count++;
		} else if (qName.equalsIgnoreCase("EpName")) {
			epData[epCount][1] = result;
			count++; 
		} else if (qName.equalsIgnoreCase("Category1")) {
			epData[epCount][2] = result;
			count++;
		} else if (qName.equalsIgnoreCase("Category2")) {
			epData[epCount][3] = result;
			count++; 
		} else if (qName.equalsIgnoreCase("VidLink")) {
			epData[epCount][4] = result;
			count++;
		} else if (qName.equalsIgnoreCase("Season")) {
			epData[epCount][5] = result;
			count++;
		} else if (qName.equalsIgnoreCase("EpNum")) {
			epData[epCount][6] = result;
			count++;
		}
		if (count + 1 > 7) {/*
			System.out.println();
			System.out.println("EpID: " + epData[epCount][0]);
			System.out.println("EpName: " + epData[epCount][1]);
			System.out.println("Category1: " + epData[epCount][2]);
			System.out.println("Category2: " + epData[epCount][3]);
			System.out.println("Vid_Link: " + epData[epCount][4]);
			System.out.println("Season: " + epData[epCount][5]);
			System.out.println("Episode: " + epData[epCount][6]);
			System.out.println();*/
			count = 0; // restart count
			if (epCount + 1 < 52) epCount++;
		}
  }

  public void characters(
	 char ch[], int start, 
	 int length) throws SAXException 
  {
	stringBuffer.append(new String(ch, start, length));
  }
  
  public String[][] getEpData() {
	return epData;
  }

}

class WriteHTML 
{
	private String[][] epData;
	private ArrayList<Integer> categoryMatches = new ArrayList<Integer>();
	private PrintWriter output;
	
	public WriteHTML(String[][] epData) {
		this.epData = epData;
	}
	 
	public void matchCategory(String category) {
		for(int i = 0; i < 52; i++) {
			if(Arrays.asList(epData[i]).contains(category)) {
				categoryMatches.add(i);
			}
		}
	}
	
	// TODO specifically calculate rows and cols it'll go through
	// fix algorith so we don't get null stuff
	public void makeCategoryTable(String category) {
		/*matchCategory(category);
		try {
		output = new PrintWriter("categories/" +
            		category.replaceAll(" ", "_") + ".html");*/
		int total = categoryMatches.size();
		System.out.println("Category: " + category + " Total: " + total);
		int pages = total / 16; // obvs is there's less that 16
								// this will return 0
								// if it's 0,  get the amount
								// that it has and devise rows/cols
								// form that.
		int page = 1;
		boolean lastPageFull = (total % 16) == 0;
		// this entire loop makes unfounded assumptionss about how much data there is
		// rows = what?  total divided by 4 or total mode 4
		int rows = (total >= 4) ? (total / 4) : 1;
		int c = 0; //counter for total amount of data.
		
		//how should I calculate d's max?
		for(int p = 0; p <= pages; p++) {
			// table tag
			System.out.println("          <table class='category_table' id='"+(p+1)+"'>");
			output.println("           <table class='category_table' id='"+(p+1)+"'>");
			
			for(int r = 0; r < 4; r++) {
				// tr tag
			    System.out.println("            <tr>");
				output.println("            <tr>");
				
				for(int d = 0; d <4; d++) {
				    /*
					if (!lastPageFull) {
						if (d == ((total % 16)-1) && (p == pages) {
							makeCell(epData[categoryMatches.get(d)]);
							/*
							writer.println("            </tr>");
							writer.println("           </table>");
							writer.println("");
							System.out.println("            </tr>");
							System.out.println("           </table>");
							return;
						}
						
					} */
					if (c + 1 == total) {
						makeCell(epData[categoryMatches.get(c)]);
						output.println("            </tr>");
						output.println("           </table>");
						
						System.out.println("            </tr>");
						System.out.println("           </table>");
						return;
					} else {
					   // make cell
					   // td tag
					   // ending tr tag
					   makeCell(epData[categoryMatches.get(c)]);
					   c++;
					}
				}
				// ending tr tag
				System.out.println("            </tr>");
				output.println("            </tr>");
			}
			
			// ending table tag
			System.out.println("           </table>");
			output.println("           </table>");
		}
		/*
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	public void makeCategoryHeader(String category) {
		String header;
		header = "<!DOCTYPE html>\n" +
		         "<html lang='en'>\n" +
				 "<head>\n" +
				 "   <meta charset='utf-8'/>\n" +
				 "   <title>Hotline to Hell Girl: " + category + "</title>\n" +
				 "   <link rel='stylesheet' type='text/css' href='hthg.css'>\n" +
				 "   <script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'>\n" +
				 "   </script>\n" +
				 "   <script type='text/javascript' src='category_box.js'></script>\n" +
				 "</head>\n" + 
				 "<body>\n" +
				 "<script type='text/javascript' src='header.js'></script>\n\n" +
				 "<div id='content_wrapper'>\n" +
                 "   <div class='content'>\n" +
                 "      <h1 class='content'>Category: <span class='category_header'> "+ category + " </span></h1>\n" + 
	             "        <div id='c_table_wrapper'>\n";

		output.println(header);
	}
	
	public void makeCategoryFooter() {
		String footer;
		footer = "        </div>\n" +
                 "    </div>\n" +
                 "</div>\n" +
                 "<div id='page_line'>\n\n" +
                 "</div>\n" +
                 "<script type='text/javascript' src='footer.js'></script>\n" +
                 "</body>\n" +
                 "</html>";
		
		output.println(footer);
	}
	public void makeCategoryPage(String category) {
		matchCategory(category);
		try {
		
		output = new PrintWriter(/*"categories/" +*/
            		category.toLowerCase().replaceAll(" ", "_") + ".html");	    		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		makeCategoryHeader(category);
		makeCategoryTable(category);
		makeCategoryFooter();
		output.close();
		categoryMatches.clear(); // get rid of results form last match
	}
	
	// Should be passed categoryMatches [d];
	public void makeCell(String[] episode) {
		String cell; 
		String videoLink = /*"episodes/" + */
		      (episode[1].toLowerCase().replaceAll("'","").replaceAll(" ", "_") + ".html");
		String thumbLink = /*images/thumbnails/" +*/episode[0]+".jpg";
		String title = episode[1];
		String category1 = episode[2];
		String category2 = episode[3];
		String spacer = ", &nbsp;";
		String category1Link = /*"categories/" + */
		       category1.toLowerCase().replaceAll(" ", "_") + ".html";
		String category2Link = "";
		if (category2 != null) {
		category2Link = /*"categories/" +*/ 
		       category2.toLowerCase().replaceAll(" ", "_") + ".html";}
		String season = episode[5];
		String epNum = episode[6];
		
		// Hold on, lets just do this. If category2 is null, just print a different
		// string. 
		String categoryString;
		String cString1 = "Category: <a class='category_link' href='"+category1Link+"'>"+category1+"</a>";
		String cString2 = "Category: <a class='category_link' href='"+category1Link+"'>"+category1+"</a>" + spacer + "\n" +
		                  "                        <a class='category_link' href='"+category2Link+"'>"+category2+"</a>";
		categoryString = (episode[3].equals("")) ? cString1 : cString2;
			   
		cell = "                 <td>\n"+
			   "                    <a href='" + videoLink + "'>\n"+
			   "                      <img src='" + thumbLink + "' alt='thumbnail' />\n"+
		       "                    </a>\n"+
			   "                    <a class='ep_title_link' href='" + videoLink + "'>\n" +
			   "                        <div class='epTitle'>" + title + "</div>\n"+
			   "                    </a>\n" +
			   "                    <div class='cat_link_wrapper'>" + categoryString + "</div>\n" +   
			   "                    <div>Season " + season + ", Episode " + epNum + "</div>\n"+
		       "                </td>";
			
		System.out.println(cell);	
		output.println(cell);
		output.println();
		//output.close();
	}
	
	// makes video page 
	public void makeEpisodePage(int episode) {
		try {
			output = new PrintWriter(
            		epData[episode][1].toLowerCase().replaceAll("'","").replaceAll(" ", "_") + ".html");	    		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		
		makeVideoHeader(epData[episode][1]);
		makeVideoContent(epData[episode]);
		makeVideoFooter();
		
		output.close();
	}
	
	public void makeVideoHeader(String episode) {
		String header;
		header =  "<!DOCTYPE html>\n" +
		          "<head>\n" +
				  "    <meta charset='utf-8' />\n" +
				  "    <title>Hotline to Hell Girl: " + episode + "</title>\n" +
				  "    <link rel='stylesheet' type='text/css' href='hthg.css'>\n" +
				  "</head>\n" + 
				  "<body>\n\n" + 
				  "<script type='text/javascript' src='header.js'></script>\n" +
				  "<div id='content_wrapper'>\n" +
				  "    <div class='content'>\n" +
				  "      <p class='content'>\n" +
				  "      <h1 class='content'>Episodes</h1>\n";
		
		output.println(header);
	}

	public void makeVideoContent(String[] episode) {
		String content;
		String videoLink = (episode[1].toLowerCase().replaceAll("'","").replaceAll(" ", "_") + ".html");
		String thumbLink = episode[0]+".jpg";
		String title = episode[1];
		String category1 = episode[2];
		String category2 = episode[3];
		String spacer = ", &nbsp;";
		String category1Link = category1.toLowerCase().replaceAll(" ", "_") + ".html";
		String category2Link = "";
		if (category2 != null) {
			category2Link = category2.toLowerCase().replaceAll(" ", "_") + ".html";}
		String season = episode[5];
		String epNum = episode[6];
		
		String categoryString;
		String cString1 = "Category: <a class='category_link' href='"+category1Link+"'>"+category1+"</a>";
		String cString2 = "Category: <a class='category_link' href='"+category1Link+"'>"+category1+"</a>" + spacer + "\n" +
		                  "                        <a class='category_link' href='"+category2Link+"'>"+category2+"</a>";
		categoryString = (episode[3].equals("")) ? cString1 : cString2;
		
		content = "      <div id='video_episode'>\n" +
				  "        <embed type='application/x-shockwave-flash'\n" +
				  "         height='350' width='480' src='" + episode[4] + "' allowFullScreen='true'/>\n" + 
	              "      </div>\n\n\n" +
                  "      <div id='data'>\n" +
                  "         <div id='video_head'>" + title + "</div>\n" +
				  "            <div id='video_ep_data'>Season " + season + ", Episode " + epNum + "</div>\n" + 
				  "            <div id='video_category'>" + categoryString + "</div>\n" +
				  "            <div id='video_box'>\n" + 
				  "                                  " +
				  "            <div id ='comment_container'>\n" +
				  "                <form>\n" +
				  "                    <label id='username_label'>Username:<input type='text' id='uName' size='25'></label>\n" +
				  "                </form>\n" +
				  "                <textarea id='box' rows='4' cols='46' placeholder='Enter your comment.'></textarea>\n" +
				  "                <button id='submit_button' type='button' onclick='processComment()'>Submit Comment</button>\n" +
				  "                <div>\n" +
				  "                    <select id='comment_sort' onclick='setSort()'>\n" +
				  "                        <option value='Newest first'>Newest first</option>\n" +
				  "                        <option value='Top comments'>Top Comments</option>\n" +
				  "                     </select>\n" + 
				  "                </div>\n" +
				  "                <div id='comments'></div></div>\n" +
				  "            </div>\n" +
                  "      </div>\n";				  
                   				  
		output.println(content);
	}
	
	public void makeVideoFooter() {
		String footer;
		footer = "     <script type='text/javascript' src='comments.js'></script>\n" +
		          "   </div>\n" +
				  "</div>\n\n" +
				  "<script type='text/javascript' src='footer.js'></script>\n" +
				  "</body>\n" +
				  "</html>";
				  
		output.println(footer);
	}

}
