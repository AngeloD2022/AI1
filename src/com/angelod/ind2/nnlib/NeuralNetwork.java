package com.angelod.ind2.nnlib;

public class NeuralNetwork {

    private int inputLayerSize;

    private int hiddenLayerCount;

    private int hiddenLayerSize;

    private int outputLayerSize;

    private Layer inputLayer;

    private Layer[] hiddenLayers;

    private Layer outputLayer;


    /**
     * Initializes a Neural Network
     * @param inputLayerSize - The amount of neurons in the input layer.
     * @param hiddenLayers - The amount of hidden layers.
     * @param hiddenLayerSize - The amount of neurons in the hidden layers.
     * @param outputLayerSize  - The amount of neurons in the output layer.
     */
    public NeuralNetwork(int inputLayerSize, int hiddenLayers, int hiddenLayerSize, int outputLayerSize){
        this.inputLayerSize = inputLayerSize;
        this.hiddenLayerCount = hiddenLayers;
        this.hiddenLayerSize = hiddenLayerSize;
        this.outputLayerSize = outputLayerSize;


        //Initialize layers
        this.inputLayer = new Layer(inputLayerSize, 0);

        this.hiddenLayers = new Layer[hiddenLayerCount];


        for(int i = 0; i < hiddenLayerCount; i++){
            if(i==0){
                this.hiddenLayers[i] = new Layer(this.hiddenLayerSize, this.inputLayerSize);
            }else{
                this.hiddenLayers[i] = new Layer(this.hiddenLayerSize, this.hiddenLayerSize);
            }
        }

        this.outputLayer = new Layer(this.outputLayerSize, this.hiddenLayerSize);

    }

    public void dump(){
        System.out.println("I: "+inputLayer);
        for (Layer l : hiddenLayers) {
            System.out.println("H: "+l);
        }
        System.out.println("O:"+outputLayer);
    }

    public double[] runNetwork(double[] input){

        double[] result = new double[outputLayerSize];

        //Initialize the input activations.
        inputLayer.setInputActivations(input);

        //Compute other activations.
        for(int i = 0; i < hiddenLayers.length; i++){
            if(i==0){
                hiddenLayers[i].computeActivations(inputLayer);
            }else{
                hiddenLayers[i].computeActivations(hiddenLayers[i-1]);
            }
        }

        //compute output
        outputLayer.computeActivations(hiddenLayers[hiddenLayers.length-1]);

        return outputLayer.getActivations();
    }




}