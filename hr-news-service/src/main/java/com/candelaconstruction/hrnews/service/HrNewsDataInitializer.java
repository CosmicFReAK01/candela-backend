package com.candelaconstruction.hrnews.service;

import com.candelaconstruction.hrnews.model.CorporateNews;
import com.candelaconstruction.hrnews.model.JobPosition;
import com.candelaconstruction.hrnews.repository.CorporateNewsRepository;
import com.candelaconstruction.hrnews.repository.JobPositionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class HrNewsDataInitializer implements CommandLineRunner {

    private final JobPositionRepository jobRepository;
    private final CorporateNewsRepository newsRepository;

    public HrNewsDataInitializer(JobPositionRepository jobRepository, CorporateNewsRepository newsRepository) {
        this.jobRepository = jobRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public void run(String... args) {
        seedJobs();
        seedNews();
    }

    private void seedJobs() {
        if (jobRepository.count() > 0) return;

        jobRepository.saveAll(Arrays.asList(
                new JobPosition("job-01", "Senior Cross-Country Pipeline Engineer", "Engineering & Projects", "Pan-India / Bihar & UP", "8-12 Years", "Full-Time",
                        "Lead field pipeline construction spreads for 36\" and 48\" transmission lines. Manage alignment, automated welding, trenching, lowering-in, and hydrotesting."),
                new JobPosition("job-02", "HDD Rig Pilot / Directional Drilling Superintendent", "Trenchless Drilling", "Project Sites / Rivers", "7+ Years", "Full-Time",
                        "Operate 250T and 450T HDD drilling rigs for major river and highway crossings. Experience with ParaTrack-2 guidance, mud engineering, and 36\"+ reaming spreads."),
                new JobPosition("job-03", "QA/QC Lead & NDT Level-III Inspector (PAUT/RT)", "Quality Assurance", "Field Inspection Hubs", "10+ Years", "Full-Time",
                        "Oversee pipeline quality management system, review automated PAUT/radiographic films, qualify welding procedure specifications (WPS/PQR), and interface with TPIA."),
                new JobPosition("job-04", "HSE Manager — Pipeline Construction Spreads", "Safety & Environment", "Pan-India Spreads", "8-10 Years", "Full-Time",
                        "Enforce Zero Harm site protocols, manage environmental compliance, conduct safety toolbox talks, and coordinate daily risk assessments across active construction spreads."),
                new JobPosition("job-05", "Mainline Welding Inspector / CSWIP 3.1", "Field Construction", "Linear Spreads", "5+ Years", "Full-Time",
                        "Monitor automatic and manual welding operations to API 1104 / ASME Section IX. Verify preheat temperatures, interpass tolerances, and joint fit-up alignment."),
                new JobPosition("job-06", "CGD Network & Steel Pipeline Project Manager", "City Gas Distribution", "Urban Regional Centres", "10-15 Years", "Full-Time",
                        "Manage urban steel pipeline networks and MDPE reticulation for City Gas Distribution projects. Supervise DRS/MRS skids, road permissions, and last-mile connectivity.")
        ));
    }

    private void seedNews() {
        if (newsRepository.count() > 0) return;

        newsRepository.saveAll(Arrays.asList(
                new CorporateNews("news-01", "CandelaConstruction Achieves 2,140m Record HDD River Crossing Beneath Ganga", "14 September 2026", "Project Milestone", "4 min read",
                        "Successful pull-back of a 36-inch high-pressure gas pipeline beneath the holy Ganga River in North Bihar sets a new regional benchmark for trenchless engineering.",
                        "CandelaConstruction Private Limited has achieved a historic milestone with the successful pullback of a 36-inch diameter, 2,140-meter continuous pipeline section beneath the riverbed of the Ganga River. Executed with a 450-tonne maxi rig and ParaTrack-2 electromagnetic guidance, the single-shot drill marks one of India's longest large-diameter river crossings completed without environmental disturbance."),
                new CorporateNews("news-02", "Company Surpasses 25 Million Consecutive Safe Man-Hours Without LTI", "28 August 2026", "HSE Achievement", "3 min read",
                        "Reaching a major occupational safety benchmark across all ongoing cross-country pipeline spreads, station piping, and CGD networks.",
                        "Our steadfast commitment to our 'Zero Harm' safety culture has reached a proud milestone: 25,000,000 safe man-hours executed without a single Lost Time Injury (LTI). This safety achievement spans 8 active construction spreads and multiple fabrication workshops across Bihar, Uttar Pradesh, Rajasthan, and Gujarat."),
                new CorporateNews("news-03", "Awarded 185 km Cross-Country Natural Gas Trunkline EPC Contract", "12 July 2026", "Contract Award", "3 min read",
                        "Major public sector undertaking awards turnkey EPC contract for 48\" high-pressure gas transmission pipeline connecting northern industrial hubs.",
                        "CandelaConstruction has been awarded a prestigious turnkey EPC contract for laying, welding, testing, and commissioning 185 km of 48-inch API 5L X70 natural gas pipeline. The scope includes 8 sectionalizing valve stations, 2 intermediate pigging terminals, and multi-barrel trenchless crossings."),
                new CorporateNews("news-04", "Fleet Expansion: Induction of Two 450-Tonne Trenchless Drilling Spreads", "04 June 2026", "Machinery & Innovation", "2 min read",
                        "Strategic investment in state-of-the-art European HDD rigs and automated CRC-Evans welding spreads to boost linear mainline productivity.",
                        "To support India's rapidly expanding One Nation One Gas Grid vision, CandelaConstruction has inducted two brand-new 450-tonne heavy-duty horizontal directional drilling rigs and six dual-torch automated welding spreads into its central logistics hub.")
        ));
    }
}
