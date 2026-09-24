package com.candelaconstruction.project.service;

import com.candelaconstruction.project.model.EquipmentItem;
import com.candelaconstruction.project.model.Project;
import com.candelaconstruction.project.model.ServiceItem;
import com.candelaconstruction.project.model.StateFootprint;
import com.candelaconstruction.project.repository.EquipmentRepository;
import com.candelaconstruction.project.repository.ProjectRepository;
import com.candelaconstruction.project.repository.ServiceRepository;
import com.candelaconstruction.project.repository.StateFootprintRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final ServiceRepository serviceRepository;
    private final EquipmentRepository equipmentRepository;
    private final StateFootprintRepository stateRepository;

    public DataInitializer(ProjectRepository projectRepository,
                           ServiceRepository serviceRepository,
                           EquipmentRepository equipmentRepository,
                           StateFootprintRepository stateRepository) {
        this.projectRepository = projectRepository;
        this.serviceRepository = serviceRepository;
        this.equipmentRepository = equipmentRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public void run(String... args) {
        seedServices();
        seedProjects();
        seedEquipment();
        seedStates();
    }

    private void seedServices() {
        if (serviceRepository.count() > 0) return;

        ServiceItem s1 = new ServiceItem();
        s1.setId("srv-01");
        s1.setN("01");
        s1.setIcon("Zap");
        s1.setTitle("Cross-Country Transmission Pipelines");
        s1.setSlug("cross-country-pipelines");
        s1.setText("Turnkey EPC laying of large-diameter high-pressure natural gas, crude oil, and petroleum product pipelines up to 48\" OD across diverse terrains.");
        s1.setStandard("API 1104 / ASME B31.8 / OISD 226");
        s1.setColor("amber");
        s1.setBullets(Arrays.asList(
                "Automated & Semi-Automated Welding spreads (GMAW / SAW / SMAW)",
                "Full-field hydrostatic testing up to 150 bar with calibrated telemetry",
                "Induction cold pipe bending up to 48\" diameter with 3D/5D radius",
                "Advanced NDT: Phased Array Ultrasonic Testing (PAUT) & Real-Time Radiography",
                "Integrated 3LPE / 3LPP field joint coating & heat shrink sleeves"
        ));
        s1.setSubcategories(Arrays.asList("Trunk Gas Lines", "Crude Transmission", "Multi-Product Slurry Lines"));

        ServiceItem s2 = new ServiceItem();
        s2.setId("srv-02");
        s2.setN("02");
        s2.setIcon("Compass");
        s2.setTitle("Trenchless HDD & Water Body Crossings");
        s2.setSlug("trenchless-hdd-crossings");
        s2.setText("Precision Horizontal Directional Drilling (HDD) and microtunnelling across major rivers, railway corridors, national highways, and congested urban intersections.");
        s2.setStandard("DCA Guidelines / ASTM F1962 / API RP 1111");
        s2.setColor("cyan");
        s2.setBullets(Arrays.asList(
                "Max single-shot crossing capability up to 2,400 meters",
                "Heavy-duty drilling rigs up to 450-tonne push/pull thrust rating",
                "Gyro Steering Tools & ParaTrack-2 electromagnetic guidance systems",
                "Environmentally safe bentonite fluid recycling and high-shear slurry units",
                "Buoyancy control and pre-tested pipe string pull-back operations"
        ));
        s2.setSubcategories(Arrays.asList("Major River Crossings", "Highway Intersections", "Railway Embankments"));

        ServiceItem s3 = new ServiceItem();
        s3.setId("srv-03");
        s3.setN("03");
        s3.setIcon("Flame");
        s3.setTitle("City Gas Distribution (CGD) Networks");
        s3.setSlug("city-gas-distribution");
        s3.setText("End-to-end development of CGD infrastructure comprising steel trunk feeder lines, MDPE distribution reticulation, and PNG/CNG consumer networks.");
        s3.setStandard("PNGRB T4S Regulations / ASME B31.8 / EN 12007");
        s3.setColor("emerald");
        s3.setBullets(Arrays.asList(
                "Carbon steel grid headers (4\" to 18\" diameter, Class 150/300/600)",
                "Underground MDPE distribution (20mm to 180mm) with electrofusion joints",
                "District Regulating Skid (DRS) & Metering Skid (MRS) fabrication",
                "CNG Daughter Booster & Mother Station piping and cascade headers",
                "Last-mile domestic riser installation and commercial PNG connections"
        ));
        s3.setSubcategories(Arrays.asList("Steel Pipeline Headers", "MDPE Reticulation", "DRS / MRS Stations"));

        ServiceItem s4 = new ServiceItem();
        s4.setId("srv-04");
        s4.setN("04");
        s4.setIcon("Wrench");
        s4.setTitle("Plant, Refinery & Station Piping");
        s4.setSlug("plant-refinery-piping");
        s4.setText("Precision fabrication, erection, and tie-in welding for sectionalizing valve (SV) stations, compressor hubs, metering skids, and refinery battery limits.");
        s4.setStandard("ASME B31.3 / OISD 118 / API 598");
        s4.setColor("orange");
        s4.setBullets(Arrays.asList(
                "CS, LTCS, Stainless Steel & Alloy steel spools pre-fabrication",
                "Heavy structural pipe racks, modular skids, and manifold assemblies",
                "Tie-in hookups during planned live plant turnarounds and shutdowns",
                "Pig launcher and receiver barrel installation (bi-directional)",
                "Valve automation, pneumatic actuator linkage, and ESD station integration"
        ));
        s4.setSubcategories(Arrays.asList("SV & IP Stations", "Pig Launcher / Receivers", "Compressor Manifolds"));

        ServiceItem s5 = new ServiceItem();
        s5.setId("srv-05");
        s5.setN("05");
        s5.setIcon("ShieldCheck");
        s5.setTitle("Hydrotesting & Pre-Commissioning");
        s5.setSlug("hydrotesting-precommissioning");
        s5.setText("Rigorous hydrostatic pressure validation, caliper pigging, geometric gauging, bulk dewatering, swabbing, vacuum drying, and nitrogen purging.");
        s5.setStandard("ASME B31.8 Chap VIII / OISD 141 / API 1110");
        s5.setColor("red");
        s5.setBullets(Arrays.asList(
                "Test pressures exceeding 150 bar with digital deadweight pressure logs",
                "Electronic EGP caliper pigging for dent and ovality profiling",
                "Super-dry air and vacuum drying achieving dew points down to -40°C",
                "Nitrogen purging and pipeline blanketing before hydrocarbon charging",
                "High-capacity centrifugal fill pumps and positive displacement triplex test units"
        ));
        s5.setSubcategories(Arrays.asList("Hydrostatic Pressure Testing", "Caliper Pigging", "Vacuum Drying"));

        ServiceItem s6 = new ServiceItem();
        s6.setId("srv-06");
        s6.setN("06");
        s6.setIcon("Activity");
        s6.setTitle("Cathodic Protection & Pipeline Integrity");
        s6.setSlug("cathodic-protection-integrity");
        s6.setText("Advanced corrosion prevention systems including Impressed Current Cathodic Protection (ICCP), sacrificial anode systems, and inline inspection (ILI) support.");
        s6.setStandard("NACE SP0169 / ISO 15589-1 / OISD 139");
        s6.setColor("cyan");
        s6.setBullets(Arrays.asList(
                "Impressed Current CP with deep-well mixed metal oxide (MMO) anode groundbeds",
                "Transformer Rectifier Units (TRU) with remote satellite/SCADA telemetry",
                "CIPS (Close Interval Potential Survey) and DCVG coating defect surveys",
                "AC/DC interference mitigation near high-voltage transmission lines",
                "Intelligent MFL & ultrasonic cleaning pig run operational support"
        ));
        s6.setSubcategories(Arrays.asList("ICCP Deep Anodes", "DCVG / CIPS Surveys", "Interference Mitigation"));

        serviceRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
    }

    private void seedProjects() {
        if (projectRepository.count() > 0) return;

        Project p1 = new Project();
        p1.setId("prj-01");
        p1.setSlug("jhbdpl-spie-capag");
        p1.setCat("cross-country");
        p1.setTag("CROSS-COUNTRY");
        p1.setTagColor("amber");
        p1.setTitle("JHBDPL Cross-Country Trunk Pipeline — 48\" Section");
        p1.setMeta("GAIL India · Bihar / UP / Jharkhand · 320 km · Completed");
        p1.setDiameter("48\" (1219mm)");
        p1.setLength("320 km");
        p1.setWallThickness("19.1mm / 25.4mm");
        p1.setPressure("100 bar (Class 600)");
        p1.setDuration("18 Months");
        p1.setLocation("Chapra – Patna – Bokaro Section");
        p1.setState("Bihar");
        p1.setClient("GAIL (India) Limited");
        p1.setProjectType("Natural Gas Transmission Pipeline");
        p1.setStatus("Completed");
        p1.setShortDesc("320 km of 48-inch API 5L X70 PSL2 natural gas trunkline connecting North and East India energy corridors through alluvial riverbeds and canal crossings.");
        p1.setDescription("Flagship energy grid project involving heavy cross-country pipeline construction through intensive agricultural zones, waterlogged terrains, and densely populated corridors.");
        p1.setScopeOfWork(Arrays.asList(
                "Right of Way (RoW) acquisition facilitation and clearing for 320 km corridor",
                "Trenching, stringing, and automated dual-torch GMAW welding of 48\" pipes",
                "100% PAUT (Phased Array Ultrasonic Testing) of over 26,000 field girth welds",
                "Installation of 14 Sectionalizing Valve (SV) stations and 2 intermediate pig receiver stations",
                "Hydrostatic testing in 25 km test blocks at 150 bar hold pressure for 24 continuous hours"
        ));
        p1.setKeyChallenges(Arrays.asList(
                "High water table in North Bihar necessitating continuous dewatering trenches",
                "Laying through prime agricultural land with zero-harm crop compensation protocols",
                "Logistical transit of 48\" 12-meter pipe joints across rural bridges"
        ));
        p1.setExecution(Arrays.asList(
                "Deployed 3 simultaneous welding spreads achieving peak output of 1.4 km per day per spread",
                "Employed CRC-Evans automatic welding heads for repeatable high-integrity joints",
                "Finished project 45 days ahead of contractual milestone with zero lost-time incidents"
        ));
        p1.setHighlights(Arrays.asList("320 KM Length", "48 Inch OD", "100 Bar Rating", "Zero LTI Record"));
        p1.setSpecLabel("Mainline OD");
        p1.setSpecValue("48\" / 1219mm");

        Project p2 = new Project();
        p2.setId("prj-02");
        p2.setSlug("ganga-river-hdd");
        p2.setCat("hdd");
        p2.setTag("HDD CROSSING");
        p2.setTagColor("cyan");
        p2.setTitle("Ganga River Trenchless Mega Crossing — HDD Spread");
        p2.setMeta("GAIL / Indian Oil · Saran (Chapra) · 2,140 m · Completed");
        p2.setDiameter("36\" (914mm)");
        p2.setLength("2,140 m single shot");
        p2.setWallThickness("22.2mm Heavy Wall");
        p2.setPressure("98 bar");
        p2.setDuration("7 Months");
        p2.setLocation("Saran (Chapra) to Patna Shore");
        p2.setState("Bihar");
        p2.setClient("GAIL (India) Limited");
        p2.setProjectType("Trenchless River Crossing");
        p2.setStatus("Completed");
        p2.setShortDesc("Record-setting 2,140-meter single-shot horizontal directional drill crossing beneath the perennial currents of the holy Ganga River in North Bihar.");
        p2.setDescription("One of India's longest single-shot 36\" pipeline river crossings executed beneath the dynamic scour bed of the Ganga River.");
        p2.setScopeOfWork(Arrays.asList(
                "Subsurface geophysical profiling and geotechnical boreholes to 45m depth",
                "Pilot hole drilling using 450T rig with Gyro guidance and electromagnetic ParaTrack-2",
                "Multi-stage reaming from 12\" to 48\" diameter using custom hole-openers",
                "Pipe string fabrication, hydrostatic test before pullback, and continuous 28-hour pullback",
                "Post-installation gauging and cathodic continuity verification"
        ));
        p2.setKeyChallenges(Arrays.asList(
                "Unconsolidated river silt and sand strata with high potential for bore collapse",
                "Strong river currents during seasonal flood cycles",
                "Managing 650-tonne pulling tension during final pipe drag"
        ));
        p2.setExecution(Arrays.asList(
                "Formulated customized polymer-bentonite mud systems to maintain hydro-bore integrity",
                "Continuous round-the-clock pullback operation completed without friction freeze",
                "Awarded National Infrastructure Excellence recognition"
        ));
        p2.setHighlights(Arrays.asList("2,140 m Drill", "450T Thrust", "36\" Diameter", "Riverbed Scour Proof"));
        p2.setSpecLabel("Drill Length");
        p2.setSpecValue("2,140 Meters");

        Project p3 = new Project();
        p3.setId("prj-03");
        p3.setSlug("mehsana-bhatinda-gspl");
        p3.setCat("cross-country");
        p3.setTag("CROSS-COUNTRY");
        p3.setTagColor("amber");
        p3.setTitle("Mehsana–Bhatinda High-Pressure Natural Gas Grid");
        p3.setMeta("GSPL India Gasnet · Rajasthan / Punjab · 185 km · Completed");
        p3.setDiameter("36\" (914mm)");
        p3.setLength("185 km");
        p3.setWallThickness("15.9mm");
        p3.setPressure("98 bar");
        p3.setDuration("14 Months");
        p3.setLocation("Bikaner – Hanumangarh Section");
        p3.setState("Rajasthan");
        p3.setClient("GSPL India Gasnet Limited");
        p3.setProjectType("Inter-State Gas Transmission");
        p3.setStatus("Completed");
        p3.setShortDesc("Laying and commissioning 185 km of 36\" pipeline through arid desert sands and undulating dune terrain in Western India.");
        p3.setDescription("Constructed across desert terrain requiring specialized dune stabilization, crawler equipment pads, and anti-abrasion rock shield pipeline coating.");
        p3.setScopeOfWork(Arrays.asList(
                "Mainline trenching through shifting sand dunes with geotextile stabilization",
                "SMAW downhill root pass and semi-automatic flux-cored arc welding fills",
                "Installation of 7 sectionalizing valve stations with remote telemetry units",
                "Hydrostatic testing using managed deep tube-well water sources",
                "Complete dry-air swabbing to dew point below -20°C"
        ));
        p3.setKeyChallenges(Arrays.asList(
                "Extreme ambient field temperatures exceeding 48°C in peak summer",
                "Shifting sand dunes requiring specialized track equipment",
                "Logistical water procurement in water-scarce desert belts"
        ));
        p3.setExecution(Arrays.asList(
                "Operated nocturnal welding and radiographic testing shifts to maintain thermal joint quality",
                "Recycled 100% of hydrotest water between test sections",
                "Commissioned pipeline with 100% first-pass hydrocarbon charging"
        ));
        p3.setHighlights(Arrays.asList("185 KM Spread", "Desert RoW", "36\" Mainline", "100% Water Recycled"));
        p3.setSpecLabel("Pipeline OD");
        p3.setSpecValue("36\" / 914mm");

        Project p4 = new Project();
        p4.setId("prj-04");
        p4.setSlug("cgd-lucknow-green-gas");
        p4.setCat("cgd");
        p4.setTag("CITY GAS (CGD)");
        p4.setTagColor("emerald");
        p4.setTitle("Lucknow–Unnao CGD Steel & MDPE Reticulation Grid");
        p4.setMeta("Green Gas Limited · Uttar Pradesh · 110 km Steel / 450 km MDPE · Ongoing");
        p4.setDiameter("8\" to 16\" Steel + 32-125mm MDPE");
        p4.setLength("560 km total");
        p4.setWallThickness("8.2mm / 10.3mm");
        p4.setPressure("49 bar (Steel) / 4 bar (MDPE)");
        p4.setDuration("24 Months");
        p4.setLocation("Lucknow Urban, Sarojini Nagar & Unnao Highway");
        p4.setState("Uttar Pradesh");
        p4.setClient("Green Gas Limited");
        p4.setProjectType("City Gas Distribution (CGD)");
        p4.setStatus("Ongoing");
        p4.setShortDesc("Integrated City Gas Distribution infrastructure supplying compressed and piped natural gas to domestic, commercial, and CNG transport sectors.");
        p4.setDescription("Massive urban pipeline deployment requiring complex traffic management, microtunneling under flyovers, and night-shift installations.");
        p4.setScopeOfWork(Arrays.asList(
                "Steel transmission feeder header (12\" and 8\" API 5L Grade B)",
                "District Regulating Stations (DRS) and Metering Regulating Stations (MRS)",
                "Underground PE100 MDPE network reticulation across residential neighborhoods",
                "Last-mile domestic service riser installation to 60,000+ consumer households",
                "CNG daughter booster station high-pressure interconnects"
        ));
        p4.setKeyChallenges(Arrays.asList(
                "Dense subterranean urban utility corridors (water, power, fiber optic)",
                "Strict urban traffic restrictions requiring silent nocturnal works",
                "Coordination with municipal municipal authorities for pavement restoration"
        ));
        p4.setExecution(Arrays.asList(
                "Extensive use of ground-penetrating radar (GPR) utility mapping",
                "Trenchless pneumatic mole boring for 85% of urban road crossings",
                "Maintaining flawless safety standards in dense residential sectors"
        ));
        p4.setHighlights(Arrays.asList("60,000+ Homes", "560 KM Network", "12 City Skids", "Urban Microtrench"));
        p4.setSpecLabel("Network Length");
        p4.setSpecValue("560 km (Steel+PE)");

        Project p5 = new Project();
        p5.setId("prj-05");
        p5.setSlug("barauni-refinery-station");
        p5.setCat("plant-piping");
        p5.setTag("PLANT & REFINERY");
        p5.setTagColor("orange");
        p5.setTitle("Barauni Refinery Offsites & Dispatch Station Piping");
        p5.setMeta("Indian Oil Corporation (IOCL) · Begusarai, Bihar · Station Piping · Completed");
        p5.setDiameter("4\" to 30\" Spools");
        p5.setLength("42,000 Inch-Meter");
        p5.setWallThickness("Sch 40 / Sch 80 / Sch 160");
        p5.setPressure("150 bar (Class 900)");
        p5.setDuration("12 Months");
        p5.setLocation("Barauni Refinery Terminal Hub");
        p5.setState("Bihar");
        p5.setClient("Indian Oil Corporation Limited");
        p5.setProjectType("Refinery Station & Manifold Piping");
        p5.setStatus("Completed");
        p5.setShortDesc("Heavy offsites interconnecting piping, pig traps, and metering skid manifold fabrication within an active operating refinery complex.");
        p5.setDescription("High-precision station piping under strict refinery safety guidelines. Completed complex hot tie-ins during a scheduled 14-day turnaround.");
        p5.setScopeOfWork(Arrays.asList(
                "Prefabrication of heavy carbon steel (ASTM A106 Gr B) and LTCS spools",
                "Installation of automated pig launchers and receivers with quick-opening closures",
                "High-pressure manifold assembly for motor-operated valves (MOVs)",
                "Pre-commissioning nitrogen testing and golden tie-in weld supervision",
                "Three-coat epoxy phenolic anti-corrosive external coating system"
        ));
        p5.setKeyChallenges(Arrays.asList(
                "Working inside operating brownfield refinery with explosive hazard zoning",
                "Complex geometric tolerances (+/- 1.5mm) on modular manifold hookups",
                "Compressed shutdown window for final flanged and welded connections"
        ));
        p5.setExecution(Arrays.asList(
                "Pre-fabricated 92% of spools in offsite climate-controlled workshop",
                "Zero hot-work permit violations over 450,000 man-hours worked",
                "Hydrostatic and helium leak tested to 100% customer satisfaction"
        ));
        p5.setHighlights(Arrays.asList("42,000 Inch-Mtr", "Class 900", "Turnaround Zero Delay", "100% PAUT / RT"));
        p5.setSpecLabel("Piping Volume");
        p5.setSpecValue("42,000 Inch-Meter");

        Project p6 = new Project();
        p6.setId("prj-06");
        p6.setSlug("brahmaputra-subsea-hdd");
        p6.setCat("hdd");
        p6.setTag("HDD CROSSING");
        p6.setTagColor("cyan");
        p6.setTitle("Brahmaputra River Multi-Barrel HDD Interconnect");
        p6.setMeta("Oil India Limited · Assam · 1,820 m · Completed");
        p6.setDiameter("24\" + 12\" Dual-Barrel");
        p6.setLength("1,820 m");
        p6.setWallThickness("19.1mm");
        p6.setPressure("100 bar");
        p6.setDuration("9 Months");
        p6.setLocation("Dibrugarh Section");
        p6.setState("Assam");
        p6.setClient("Oil India Limited (OIL)");
        p6.setProjectType("Trenchless Multi-Barrel Crossing");
        p6.setStatus("Completed");
        p6.setShortDesc("Dual-barrel trenchless crossing beneath the braided Brahmaputra river channels with severe monsoon flooding challenges.");
        p6.setDescription("Challenging geological conditions with gravelly boulder layers overcome using rock-reamers and mud motors.");
        p6.setScopeOfWork(Arrays.asList(
                "Dual pilot bore drilling spaced 15 meters apart beneath main riverbed",
                "Rock-drilling through alluvial gravel using specialized PDC reamers",
                "Dual 24-inch and 12-inch steel pipe pull-backs"
        ));
        p6.setKeyChallenges(Arrays.asList("Heavy riverbed gravel and fast braided currents"));
        p6.setExecution(Arrays.asList("Completed before onset of annual Brahmaputra flash floods"));
        p6.setHighlights(Arrays.asList("Dual Barrel", "1,820m Drill", "Fast Floods Managed"));
        p6.setSpecLabel("Drill Length");
        p6.setSpecValue("1,820 m");

        projectRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
    }

    private void seedEquipment() {
        if (equipmentRepository.count() > 0) return;

        equipmentRepository.saveAll(Arrays.asList(
                new EquipmentItem("Horizontal Directional Drilling (HDD) Rigs", "Trenchless River & Highway Crossings", "450-Tonne & 250-Tonne Pullback", "4 Spreads", "Herrenknecht / Prime Drilling"),
                new EquipmentItem("Side Booms / Pipe Layers", "Trench Lowering-In & Handling", "40-Tonne & 70-Tonne Lift Capacity", "16 Units", "Caterpillar 572R / Komatsu"),
                new EquipmentItem("Hydraulic Pipe Bending Machines", "Cold Field Bending (3D / 5D)", "Up to 48\" OD Capability", "6 Units", "CRC-Evans / Superior"),
                new EquipmentItem("Automatic Dual-Torch Welding Systems", "Mainline Girth Weld Productivity", "GMAW / SAW Dual Torch", "8 Complete Spreads", "CRC-Evans / Lincoln Electric"),
                new EquipmentItem("Internal Pneumatic Line-Up Clamps", "Pipe Alignment & Rapid Root Tack", "12\" to 48\" with Copper Backup Shoes", "12 Units", "CRC-Evans"),
                new EquipmentItem("Triplex High-Pressure Hydrotest Pumps", "Mainline Pressure Testing & Caliper Runs", "Up to 250 Bar (3600 psi)", "8 Spreads", "Gardner Denver / Kamat"),
                new EquipmentItem("High-Delivery Rotary Air Compressors", "Swabbing, Dewatering & Air Drying", "1,200 CFM @ 25 Bar", "14 Units", "Atlas Copco / Ingersoll Rand"),
                new EquipmentItem("Phased Array Ultrasonic (PAUT) Systems", "Non-Destructive Weld Examination", "Real-Time 100% Volumetric Scan", "6 Systems", "Olympus OmniScan X3")
        ));
    }

    private void seedStates() {
        if (stateRepository.count() > 0) return;

        stateRepository.saveAll(Arrays.asList(
                new StateFootprint("bihar", "Bihar", 8, 480, "Active Base & Hub"),
                new StateFootprint("up", "Uttar Pradesh", 6, 360, "Ongoing Trunklines"),
                new StateFootprint("rajasthan", "Rajasthan", 4, 310, "Desert Corridors"),
                new StateFootprint("gujarat", "Gujarat", 5, 290, "Coastal Terminals"),
                new StateFootprint("jharkhand", "Jharkhand", 3, 210, "Industrial Grids"),
                new StateFootprint("punjab", "Punjab", 3, 175, "High-Pressure Gas Lines"),
                new StateFootprint("assam", "Assam", 2, 120, "HDD River Crossings"),
                new StateFootprint("maharashtra", "Maharashtra", 3, 195, "CGD Networks")
        ));
    }
}
