package com.demoblaze.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Excel {

    private Excel() {
    }

    public record LogEntry(String level, String logger, String message, String status, String thread, String tester) {
    }

    public static List<Map<String, String>> leerDatosDeHojaDeExcel(String rutaDeExcel, String hojaDeExcel) throws IOException {
        List<Map<String, String>> arrayListDatoPlanTrabajo = new ArrayList<>();
        Map<String, String> informacionProyecto;

        try (FileInputStream inputStream = new FileInputStream(new File(rutaDeExcel));
             XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet newSheet = newWorkbook.getSheet(hojaDeExcel);
            Iterator<Row> rowIterator = newSheet.iterator();
            Row titulos = rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                informacionProyecto = new HashMap<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    Cell titleCell = titulos.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String title = titleCell.toString();

                    switch (cell.getCellType()) {
                        case STRING:
                            informacionProyecto.put(title, cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            informacionProyecto.put(title, String.valueOf((long) cell.getNumericCellValue()));
                            break;
                        case BLANK:
                            informacionProyecto.put(title, "");
                            break;
                        default:
                    }
                }
                arrayListDatoPlanTrabajo.add(informacionProyecto);
            }
        }

        return arrayListDatoPlanTrabajo;
    }


    public static void escribirDatosEnHojaDeExcel(String rutaDeExcel, String hojaDeExcel, LogEntry logEntry) throws IOException {
        File file = new File(rutaDeExcel);

        try (FileInputStream inputStream = file.exists() ? new FileInputStream(file) : null;
             XSSFWorkbook workbook = inputStream != null ? new XSSFWorkbook(inputStream) : new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XSSFSheet sheet = workbook.getSheet(hojaDeExcel);
            if (sheet == null) {
                sheet = workbook.createSheet(hojaDeExcel);
            }

            XSSFCellStyle headerStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            int rowCount = sheet.getLastRowNum();
            if (rowCount == -1 && sheet.getRow(0) == null) {
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Fecha ejecucion");
                headerRow.createCell(2).setCellValue("Nivel de registro");
                headerRow.createCell(3).setCellValue("Tipo prueba");
                headerRow.createCell(4).setCellValue("Nombre caso de prueba");
                headerRow.createCell(5).setCellValue("Status");
                headerRow.createCell(6).setCellValue("Hilo");
                headerRow.createCell(7).setCellValue("Tester");
                for (int i = 0; i < 8; i++) {
                    headerRow.getCell(i).setCellStyle(headerStyle);
                }
                rowCount = 0;
            }

            Row row = sheet.createRow(rowCount + 1);
            String id = String.valueOf(rowCount + 1);
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(timestamp);
            row.createCell(2).setCellValue(logEntry.level());
            row.createCell(3).setCellValue(logEntry.logger());
            row.createCell(4).setCellValue(logEntry.message());
            row.createCell(5).setCellValue(logEntry.status());
            row.createCell(6).setCellValue(logEntry.thread());
            row.createCell(7).setCellValue(logEntry.tester());

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
        }
    }
}