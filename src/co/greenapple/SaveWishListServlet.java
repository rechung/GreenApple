package co.greenapple;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.greenapple.operations.SaveData;
import co.greenapple.utilities.CallbackUtilities;
import co.greenapple.utilities.JsonpfyProperties;
import co.greenapple.utilities.URLUtilities;

@SuppressWarnings("serial")
public class SaveWishListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (JsonpfyProperties.OPEN) {

			response.getWriter().println(saveData(request));

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.getWriter().println(saveData(request));

	}

	private String saveData(HttpServletRequest request) {

		String answer = null;

		if (JsonpfyProperties.OPEN || JsonpfyProperties.authenticate(request)) {

			String teacherNameSchoolClass = URLUtilities.decode(request
					.getParameter("teacherNameSchoolAndClass"));
			String teacherEmail = URLUtilities.decode(request
					.getParameter("teacherEmail"));
			String itemName = URLUtilities.decode(request
					.getParameter("itemName"));
			String itemLink = URLUtilities.decode(request
					.getParameter("itemLink"));
			String itemUnitPrice = URLUtilities.decode(request
					.getParameter("itemUnitPrice"));
			String itemQuantity = URLUtilities.decode(request
					.getParameter("itemQuantity"));

			String[] fieldsKind = new String[] { "String", "String", "String",
					"String", "String", "String", "String" };

			String[] fieldsName = new String[] { "teacherNameSchoolClass",
					"teacherEmail", "timestamp", "itemName", "itemLink",
					"itemUnitPrice", "itemQuantity" };

			String[] fieldsValue = new String[] { teacherNameSchoolClass,
					teacherEmail, System.currentTimeMillis() + "", itemName,
					itemLink, itemUnitPrice, itemQuantity };

			String itemId = SaveData.doPut("WishList", null, fieldsKind,
					fieldsName, fieldsValue, "true");

			answer = CallbackUtilities.getCallback(
					request.getParameter("callback"), itemId);

		}
		return answer;
	}

}
