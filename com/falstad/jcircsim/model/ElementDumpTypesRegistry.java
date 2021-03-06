package com.falstad.jcircsim.model;

import com.falstad.jcircsim.ElementBuilder;
import com.falstad.jcircsim.element.*;
import com.falstad.jcircsim.view.Scope;

import java.awt.*;

/**
 * Stores Integer(Char number) to ElmClass relation.
 * Used for:
 *   ) Circuit Import\Export.
 *   ) Hotkey identification for some elemetnts
 *
 * dumpTypes["a"] -> OpAmpElm
 * dumpTypes["c"] -> CapacitorElm
 * dumpTypes[167] -> ADCElm
 */

public class ElementDumpTypesRegistry
{
    public Class dumpTypes[];

    public ElementDumpTypesRegistry()
    {
        dumpTypes = new Class[300];

        reserveScopeCharacters();

        registerAllElements();
    }

    public void register(Class elmClass)
    {
        CircuitElm elm = ElementBuilder.build(elmClass, 0, 0);

        int dumpType = elm.getDumpType();
        if (dumpType == 0)
        {
            System.out.println("no dump type: " + elmClass);
            return;
        }
        Class dumpClass = elm.getDumpClass();
        if (dumpTypes[dumpType] == dumpClass)
            return;
        if (dumpTypes[dumpType] != null)
        {
            System.out.println("dump type conflict: " + elmClass + " " + dumpTypes[dumpType]);
            return;
        }
        dumpTypes[dumpType] = dumpClass;
    }

    private void registerAllElements()
    {
        register(WireElm.class);
        register(ResistorElm.class);

        /** Passive Components */
        register(CapacitorElm.class);
        register(InductorElm.class);
        register(SwitchElm.class);
        register(PushSwitchElm.class);
        register(Switch2Elm.class);
        register(PotElm.class);
        register(TransformerElm.class);
        register(TappedTransformerElm.class);
        register(TransLineElm.class);
        register(RelayElm.class);
        register(MemristorElm.class);
        register(SparkGapElm.class);

        /** Inputs/Outputs */
        register(GroundElm.class);
        register(DCVoltageElm.class);
        register(ACVoltageElm.class);
        register(RailElm.class);
        register(ACRailElm.class);
        register(SquareRailElm.class);
        register(OutputElm.class);
        register(LogicInputElm.class);
        register(LogicOutputElm.class);
        register(ClockElm.class);
        register(SweepElm.class);
        register(VarRailElm.class);
        register(AntennaElm.class);
        register(CurrentElm.class);
        register(LEDElm.class);
        register(LampElm.class);

        /** Active Components */
        register(DiodeElm.class);
        register(ZenerElm.class);
        register(NTransistorElm.class);
        register(PTransistorElm.class);
        register(OpAmpElm.class);
        register(OpAmpSwapElm.class);
        register(NMosfetElm.class);
        register(PMosfetElm.class);
        register(NJfetElm.class);
        register(PJfetElm.class);
        register(AnalogSwitchElm.class);
        register(AnalogSwitch2Elm.class);
        register(SCRElm.class);
        // register("Add Varactor/Varicap", VaractorElm.class);
        register(TunnelDiodeElm.class);
        register(TriodeElm.class);
        // register("DiacElm.class);
        // register("TriacElm.class);
        // register("Add Photoresistor", PhotoResistorElm.class);
        // register("ThermistorElm.class);
        register(CC2Elm.class);
        register(CC2NegElm.class);

        /** Logic Gates */
        register(InverterElm.class);
        register(NandGateElm.class);
        register(NorGateElm.class);
        register(AndGateElm.class);
        register(OrGateElm.class);
        register(XorGateElm.class);

        /** Chips */
        register(DFlipFlopElm.class);
        register(JKFlipFlopElm.class);
        register(SevenSegElm.class);
        register(VCOElm.class);
        register(PhaseCompElm.class);
        register(CounterElm.class);
        register(DecadeElm.class);
        register(TimerElm.class);
        register(DACElm.class);
        register(ADCElm.class);
        register(LatchElm.class);

        /** Other */
        register(TextElm.class);
        register(ProbeElm.class);
    }

    private void reserveScopeCharacters()
    {
        dumpTypes[(int) 'o'] = Scope.class;
        dumpTypes[(int) 'h'] = Scope.class;
        dumpTypes[(int) '$'] = Scope.class;
        dumpTypes[(int) '%'] = Scope.class;
        dumpTypes[(int) '?'] = Scope.class;
        dumpTypes[(int) 'B'] = Scope.class;
    }
}
