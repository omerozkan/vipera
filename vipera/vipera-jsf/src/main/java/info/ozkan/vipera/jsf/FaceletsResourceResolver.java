package info.ozkan.vipera.jsf;

import java.net.URL;

import javax.faces.view.facelets.ResourceResolver;


public class FaceletsResourceResolver extends ResourceResolver {

	private ResourceResolver parent;
	private String basePath;
	
	public FaceletsResourceResolver(ResourceResolver parent)
	{
		this.parent = parent;
		this.basePath = "/META-INF/resources";
	}
	
	@Override
	public URL resolveUrl(String path) {
		URL url = parent.resolveUrl(path);
		if(url == null)
		{
			url = getClass().getResource(basePath + path);
		}
		return url;
	}

}
