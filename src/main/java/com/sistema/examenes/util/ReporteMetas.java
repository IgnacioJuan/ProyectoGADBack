package com.sistema.examenes.util;

import com.sistema.examenes.dto.Poas_Indicadores_DTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ReporteMetas {

    public byte[] exportToPdfMetas(List<Poas_Indicadores_DTO> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    public byte[] exportToXls(List<Poas_Indicadores_DTO> list) throws JRException, FileNotFoundException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    //Genera reporte, tomando la lista enviada y seteando los parametros necesarios
    private JasperPrint getReport(List<Poas_Indicadores_DTO> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("datasource", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:Reporte_Metas_.jrxml")
                        .getAbsolutePath()), params, new JRBeanCollectionDataSource(list));

        return report;
    }
}
