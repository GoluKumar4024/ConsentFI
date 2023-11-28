package com.technocrats.aa;

//import com.technocrats.aa.model.ConsentArtefactDetail;
import com.technocrats.aa.repo.ConsentDetailRepo;
import com.technocrats.aa.repo.ConsentRequestDetailRepo;
import com.technocrats.aa.services.AAService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AaSvcApplicationTests {

	 @Autowired
	 private ConsentDetailRepo consentDetailRepo;

	 @Autowired
	 private AAService aaService;

	 @Autowired
	 private ConsentRequestDetailRepo consentRequestDetailRepo;


	@Test
	void testCOnsentArtefactRepo() {
		 // ConsentArtefactDetail detail = consentDetailRepo.findByConsentId("bc2573c9-2b05-42af-a364-71766c8a0742");
		// System.out.println(detail);
		// List<ConsentArtefactDetail> details = consentDetailRepo.findAllFailedArtefactFetch("bc2573c9-2b05-42af-a364-71766c8a0742");
		// System.out.println(details);
		// detail.setRequestId("1a42bde7-d623-440a-ad27-122ad941b864");
		// detail.setConsentHandleId("087d365c-acb2-416d-92dc-f92b5bac3371");
		// consentDetailRepo.save(detail);
		// consentDetailRepo.updateWithConsentHandleAndRequestId("bc2573c9-2b05-42af-a364-71766c8a0742", "1a42bde7-d623-440a-ad27-122ad941b864","087d365c-acb2-416d-92dc-f92b5bac3371");
//		System.out.println(cnt);
		// aaService.processConsent("bc2573c9-2b05-42af-a364-71766c8a0742");
		// System.out.println();
	}

}
