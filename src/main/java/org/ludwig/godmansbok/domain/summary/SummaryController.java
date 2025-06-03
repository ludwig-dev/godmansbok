package org.ludwig.godmansbok.domain.summary;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients/{clientId}/summary")
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping
    public SummaryDTO getFullSummary(
            @PathVariable Long clientId,
            @RequestParam(required = false) Integer year,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long godmanId = Long.parseLong(userDetails.getUsername());
        int queryYear = (year != null) ? year : java.time.Year.now().getValue();
        return summaryService.getFullSummaryForYear(godmanId, clientId, queryYear);
    }
}
