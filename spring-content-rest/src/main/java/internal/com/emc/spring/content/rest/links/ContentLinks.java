package internal.com.emc.spring.content.rest.links;

import java.io.Serializable;

import org.springframework.data.rest.webmvc.BaseUri;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;

import com.emc.spring.content.commons.annotations.ContentId;
import com.emc.spring.content.commons.utils.BeanUtils;

public class ContentLinks  {

	private BaseUri baseUri = null;
	
	public ContentLinks(String baseUri) {
		this.baseUri = new BaseUri(baseUri);
	}
	
	public LinkBuilder linkFor() {
		return new ContentLinksBuilder(baseUri.getUriComponentsBuilder());
	}

	public Link linkToContent(final Object content) {
		return new Link(linkFor().slash(new Identifiable<Serializable>() {

			public Serializable getId() {
				return BeanUtils.getFieldWithAnnotation(content, ContentId.class).toString();
			}
		}).toUri().toString());
	}
}