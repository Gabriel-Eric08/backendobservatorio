import { prisma } from "../../lib/prisma.js";
import { StatusEnum } from "../../generated/prisma/enums.js";
import fs from "fs";
import path from "path";
import csv from "csv-parser";

export interface CreateDataSetServiceDTO {
  title: string;
  category: string;
  department: string;
  contactEmail: string;
  description?: string;
  fileName: string;
  init_time: Date;
  end_time: Date;
}

export async function createDataSetService(
  data: CreateDataSetServiceDTO
) {
  try {
    const dataSet = await prisma.dataSet.create({
      data: {
        title: data.title,
        category: data.category,
        department: data.department,
        contactEmail: data.contactEmail,
        description: data.description,
        fileName: data.fileName,
        init_time: data.init_time,
        end_time: data.end_time,
        status: StatusEnum.PENDING,
      },
    });

    return {
      success: true,
      dataSet,
    };
  } catch (error) {
    console.error("Error creating dataset:", error);

    return {
      success: false,
      dataSet: null,
    };
  }
}
export async function getAllDataSetsService() {
  try {
    const dataSets = await prisma.dataSet.findMany({
      orderBy: {
        createdAt: "desc",
      },
    });

    return {
      success: true,
      dataSets,
    };
  } catch (error) {
    console.error("Error fetching datasets:", error);

    return {
      success: false,
      dataSets: [],
    };
  }
}
export async function updateDataSetStatusService(
  id: number,
  status: StatusEnum
) {
  try {
    const updated = await prisma.dataSet.update({
      where: { id },
      data: { status },
    });

    return {
      success: true,
      dataSet: updated,
    };
  } catch (error) {
    console.error("Error updating status:", error);

    return {
      success: false,
      dataSet: null,
    };
  }
}

export async function getDataSetWithCsvService(id: number) {
  try {
    const dataset = await prisma.dataSet.findUnique({
      where: { id },
    });

    if (!dataset) {
      return {
        success: false,
        error: "Dataset not found",
      };
    }

    const filePath = path.resolve("uploads", dataset.fileName);

    if (!fs.existsSync(filePath)) {
      return {
        success: false,
        error: "File not found",
      };
    }

    const rows: any[] = [];
    let headers: string[] = [];

    await new Promise<void>((resolve, reject) => {
      fs.createReadStream(filePath)
        .pipe(csv())
        .on("headers", (h) => {
          headers = h;
        })
        .on("data", (data) => {
          rows.push(data);
        })
        .on("end", () => resolve())
        .on("error", (err) => reject(err));
    });

    return {
      success: true,
      dataset,
      csv: {
        headers,
        rows,
      },
    };
  } catch (error) {
    console.error(error);

    return {
      success: false,
      error: "Internal error",
    };
  }
}