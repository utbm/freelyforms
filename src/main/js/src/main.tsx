import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { createApiClient } from "./apiClient/client";
import { DevTools } from "jotai-devtools";

import "./index.css";
import Home from "./pages/Home";
import FourOFour from "./pages/404";
import { PrefabCreation } from "./pages/prefabCreation";

export const apiclient = createApiClient(
	(method, url, params) =>
		fetch(url, {
			method,
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(params),
		}).then((res) => res.json()),
	"http://localhost:8080",
);

const router = createBrowserRouter([
	{
		path: "/",
		element: <Home />,
		errorElement: <FourOFour />,
	},
	{
		path: "/prefab-creation",
		element: <PrefabCreation />,
		errorElement: <FourOFour />,
	},
]);

const root = document.getElementById("root");

if (!root) {
	throw new Error("Root element not found");
}

ReactDOM.createRoot(root).render(
	<React.StrictMode>
		<DevTools />
		<RouterProvider router={router} />
	</React.StrictMode>,
);
