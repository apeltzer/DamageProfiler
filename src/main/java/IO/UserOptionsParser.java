package IO;

import org.apache.commons.cli.*;

/**
 * Created by neukamm on 01.08.16.
 */
public class UserOptionsParser {

    private static final String CLASS_NAME = "User option parser";
    private String[] args;
    private Communicator communicator;


    public UserOptionsParser(String[] args, Communicator c){
        this.args = args;
        this.communicator = c;
        parse();
    }

    private void parse(){

        // create command line parameters
        Options helpOptions = new Options();
        helpOptions.addOption("h", "help", false, "show this help page");
        Options options = new Options();
        options.addOption("h", "help", false, "show this help page");
        options.addOption(OptionBuilder.withLongOpt("input")
                .withArgName("INPUT")
                .withDescription("The input sam/bam file")
                .isRequired()
                .hasArg()
                .create("i"));
        options.addOption(OptionBuilder.withLongOpt("reference")
                .withArgName("REFERENCE")
                .withDescription("The reference file")
                .hasArg()
                .create("r"));
        options.addOption(OptionBuilder.withLongOpt("output")
                .withArgName("OUTPUT")
                .withDescription("The output folder")
                .isRequired()
                .hasArg()
                .create("o"));
        options.addOption(OptionBuilder.withLongOpt("threshold")
                .withArgName("THRESHOLD")
                .withDescription("Number of bases which are considered for plotting nucleotide misincorporations")
                .hasArg()
                .create("t"));
        options.addOption(OptionBuilder.withLongOpt("specie")
                .withArgName("SPECIE")
                .withDescription("RNAME flag SAM record (Reference sequence name)")
                .hasArg()
                .create("s"));
        options.addOption(OptionBuilder.withLongOpt("specieslist file")
                .withArgName("SPECIES LIST")
                .withDescription("List with species for which damage profile has to be calculated.")
                .hasArg()
                .create("sf"));
        options.addOption(OptionBuilder.withLongOpt("length")
                .withArgName("LENGTH")
                .withDescription("Number of bases which are considered for frequency computations.")
                .hasArg()
                .create("l"));

        options.addOption(OptionBuilder.withLongOpt("title")
                .withArgName("TITLE")
                .withDescription("Title used for all plots. Default: filepath of input SAM/BAM file.")
                .hasArg()
                .create("title"));

        options.addOption(OptionBuilder.withLongOpt("yaxis")
                .withArgName("YAXIS")
                .withDescription("Maximal value on y axis.")
                .hasArg()
                .create("yaxis"));


        Option mapped_and_merged = new Option("merged", "all_mapped_and_merged_reads", false,
                "Use all mapped and merged reads to calculate damage plot instead of using all mapped reads. The SAM/BAM entry must start with 'M_', otherwise " +
                        " it will be skipped. Default: false ");
        options.addOption(mapped_and_merged);

        Option use_all_reads = new Option("useall", "use_all_reads", false,
                "Use all reads (mapped and unmapped) to calculate damage plot. Default: false ");
        options.addOption(use_all_reads);


        HelpFormatter helpformatter = new HelpFormatter();
        CommandLineParser parser = new BasicParser();

        if(args.length < 2){
            helpformatter.printHelp(CLASS_NAME, options);
            System.exit(0);
        }

        try {
            CommandLine cmd = parser.parse(helpOptions, args);
            if (cmd.hasOption('h')) {
                helpformatter.printHelp(CLASS_NAME, options);
                System.exit(0);
            }
        } catch (ParseException e1) {
        }


        try {
            CommandLine cmd = parser.parse(options, args);

            // input files

            if (cmd.hasOption('i')) {
                communicator.setInput(cmd.getOptionValue('i'));
            }
            if (cmd.hasOption('r')) {
                communicator.setReference(cmd.getOptionValue('r'));
            }
            if (cmd.hasOption('o')) {
                communicator.setOutfolder(cmd.getOptionValue('o'));
            }

            // damage calculation

            if (cmd.hasOption('s')) {
                communicator.setSpecies_ref_identifier(cmd.getOptionValue('s'));
            }
            if (cmd.hasOption("sf")) {
                communicator.setSpecieslist_filepath(cmd.getOptionValue("sf"));
            }
            if (cmd.hasOption('l')) {
                communicator.setLength(Integer.parseInt(cmd.getOptionValue('l')));
            }
            if(cmd.hasOption("all_mapped_and_merged_reads")) {
                communicator.setUse_merged_and_mapped_reads(true);
            }

            if(cmd.hasOption("use_all_reads")) {
                communicator.setUse_all_reads(false);
            }

            // Plotting

            if(cmd.hasOption("title")) {
                communicator.setTitle_plots(cmd.getOptionValue("title"));
            }

            if(cmd.hasOption("yaxis")) {
                communicator.setyAxis(Double.parseDouble(cmd.getOptionValue("yaxis")));
            }

            if (cmd.hasOption('t')) {
                communicator.setThreshold(Integer.parseInt(cmd.getOptionValue('t')));
            }


        } catch (ParseException e) {
            helpformatter.printHelp(CLASS_NAME, options);
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }
}
