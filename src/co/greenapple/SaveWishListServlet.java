package co.greenapple;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.greenapple.utilities.CallbackUtilities;
import co.greenapple.utilities.Operations;
import co.greenapple.utilities.URLUtilities;

import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class SaveWishListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// response.getWriter().println(save(request));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.getWriter().println(save(request));
	}

	private String save(HttpServletRequest request) {

		String answer = null;

		// if (JsonpfyProperties.authenticate(request)) {

		String itemId = saveData(request);

		answer = CallbackUtilities.getCallback(
				request.getParameter("callback"), itemId);

		// }
		return answer;
	}

	private String saveData(HttpServletRequest request) {

		Entity entity = new Entity("WishList");

		entity.setProperty("timestamp", System.currentTimeMillis() + "");

		entity.setProperty("teacherNameSchoolAndClass", URLUtilities
				.decode(request.getParameter("teacherNameSchoolAndClass")));
		entity.setProperty("teacherEmail",
				URLUtilities.decode(request.getParameter("teacherEmail")));
		entity.setProperty("itemName",
				URLUtilities.decode(request.getParameter("itemName")));
		entity.setProperty("itemLink",
				URLUtilities.decode(request.getParameter("itemLink")));
		entity.setProperty("itemQuantity",
				URLUtilities.decode(request.getParameter("itemQuantity")));
		entity.setProperty("itemUnitPrice",
				URLUtilities.decode(request.getParameter("itemUnitPrice")));

		return Operations.save(entity);
	}

}
