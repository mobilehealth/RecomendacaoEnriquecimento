package mobilehealth.prc.analyzer.semantic.augmentation;

import java.net.URL;

import mobilehealth.prc.analyzer.MetadataMap;

public interface SemanticAugmentation {

	public void finalize();

	public void processURLWithMetadata(URL url, MetadataMap metadata);

	public void processDataWithMetadata(String content, MetadataMap metadata);
}