package co.greenapple;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.greenapple.utilities.CallbackUtilities;
import co.greenapple.utilities.Operations;
import co.greenapple.utilities.URLUtilities;

import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

@SuppressWarnings("serial")
public class WishListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// response.getWriter().println(list(request));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.getWriter().println(list(request));
	}

	private String list(HttpServletRequest request) {

		String answer = null;

		// if (JsonpfyProperties.authenticate(request)) {

		JSONArray entities = listData(URLUtilities.decode(request
				.getParameter("teacherEmail")));

		answer = CallbackUtilities.getCallback(
				request.getParameter("callback"), entities.toString());

		// }

		return answer;
	}

	private JSONArray listData(String teacherEmail) {

		Query query = new Query("WishList");
		query.setFilter(new FilterPredicate("teacherEmail",
				FilterOperator.EQUAL, teacherEmail));
		return Operations.jsonQuery(query);
	}

}
