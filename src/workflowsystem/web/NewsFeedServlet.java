package workflowsystem.web;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import workflowsystem.data.maincontrol.Company;
import workflowsystem.data.maincontrol.CompanyDAO;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

public class NewsFeedServlet extends HttpServlet
{
	private Logger logger = Logger.getLogger(this.getClass());
	//private static DataSource dataSource;

	/*public static void setDataSource(DataSource dataSource)
	{
		NewsFeedServlet.dataSource = dataSource;
	}*/

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("My Local News Feed");
		feed.setLink("http://localhost:8080/publisher");
		feed.setDescription("This feed was created using ROME");		
		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		
		List<Company> companies = new CompanyDAO().findAll();
		Iterator<Company> it = companies.iterator();
		while (it.hasNext()) {
		   Company company = (Company) it.next();
		   String companyCode = company.getCompanyCode();
		   String companyName = company.getCompanyName();		   
		   SyndEntry entry = new SyndEntryImpl();
		   SyndContent syndContent = new SyndContentImpl();
		   syndContent.setType("text/plain");
		   syndContent.setValue(companyName);
		   entry.setTitle(companyCode);
		   entry.setLink(companyName);		   
		   entries.add(entry);
		}
		
		/*try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select * from news_item;");
            
            //SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
            
            while (resultSet.next()) 
            {
                String title = resultSet.getString("title");
                String url = resultSet.getString("url");
                Date pubDate = resultSet.getDate("pubDate");
                SyndEntry entry = new SyndEntryImpl();
                entry.setTitle(title);
                entry.setLink(url);
                entry.setPublishedDate(pubDate);
                entries.add(entry);
            }
            connection.close();
        } 
        catch (SQLException e) 
        {
            throw new ServletException(e);
        } */

        resp.setContentType("text/xml");

        feed.setEntries(entries);
        Writer writer = resp.getWriter();
        SyndFeedOutput output = new SyndFeedOutput();
        try 
        {
            output.output(feed, writer);
        } 
        catch (FeedException e) 
        {
            logger.error("", e);
        }
		
	}		
	
}
