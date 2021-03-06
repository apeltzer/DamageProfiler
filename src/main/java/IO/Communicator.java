package IO;

/**
 * Created by neukamm on 11.11.2016.
 */
public class Communicator {

    // file options
    private String input;
    private String reference="";
    private String outfolder;
    private String specieslist_filepath = null;

    // damage calculation
    private int threshold = 25;

    private double yAxis;
    private int length = 100;
    private boolean use_merged_and_mapped_reads = false;
    private boolean use_all_reads = false;

    // specie filtering
    private String species_ref_identifier = null;

    // plot settings
    private String title_plots;



    public Communicator(){

    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOutfolder() {
        return outfolder;
    }

    public void setOutfolder(String outfolder) {
        this.outfolder = outfolder;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSpecies_ref_identifier() {
        return species_ref_identifier;
    }

    public void setSpecies_ref_identifier(String species_ref_identifier) {
        this.species_ref_identifier = species_ref_identifier;
    }

    public boolean isUse_merged_and_mapped_reads() {
        return use_merged_and_mapped_reads;
    }

    public void setUse_merged_and_mapped_reads(boolean use_merged_and_mapped_reads) {
        this.use_merged_and_mapped_reads = use_merged_and_mapped_reads;
    }

    public boolean isUse_all_reads() {
        return use_all_reads;
    }

    public void setUse_all_reads(boolean use_all_reads) {
        this.use_all_reads = use_all_reads;
    }

    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    public double getyAxis() {
        return yAxis;
    }

    public String getTitle_plots() {
        return title_plots;
    }

    public void setTitle_plots(String title_plots) {
        this.title_plots = title_plots;
    }

    public String getSpecieslist_filepath() {
        return specieslist_filepath;
    }

    public void setSpecieslist_filepath(String specieslist_filepath) {
        this.specieslist_filepath = specieslist_filepath;
    }



}
