import { Request, Response } from "express";
import { createDataSetService } from "./dataset.services.js";
import { getAllDataSetsService } from "./dataset.services.js";
import { updateDataSetStatusService } from "./dataset.services.js";
import { StatusEnum } from "../../generated/prisma/enums.js";
import { getDataSetWithCsvService } from "./dataset.services.js";

export const createDataSetController = async (
  req: Request,
  res: Response
) => {
  try {
    if (!req.file) {
      return res.status(400).json({
        success: false,
        error: "CSV file is required",
      });
    }

    if (!req.body.data) {
      return res.status(400).json({
        success: false,
        error: "Data field is required",
      });
    }


    const parsedData = JSON.parse(req.body.data);

    const {
      title,
      category,
      department,
      contactEmail,
      description,
      init_time,
      end_time,
    } = parsedData;

    if (
      !title ||
      !category ||
      !department ||
      !contactEmail ||
      !init_time ||
      !end_time
    ) {
      return res.status(400).json({
        success: false,
        error: "All required dataset fields must be provided",
      });
    }

    const result = await createDataSetService({
      title,
      category,
      department,
      contactEmail,
      description,
      fileName: req.file.filename,
      init_time: new Date(init_time),
      end_time: new Date(end_time),
    });

    if (!result.success) {
      return res.status(500).json(result);
    }

    return res.status(201).json(result);

  } catch (error) {
    console.error(error);

    return res.status(400).json({
      success: false,
      error: "Invalid JSON format in data field",
    });
  }
};

export const getAllDataSetsController = async (
  _req: Request,
  res: Response
) => {
  try {
    const result = await getAllDataSetsService();

    if (!result.success) {
      return res.status(500).json(result);
    }

    return res.status(200).json(result);

  } catch (error) {
    return res.status(500).json({
      success: false,
      error: "Internal server error",
    });
  }
};
export const approveDataSetController = async (
  req: Request,
  res: Response
) => {
  const id = Number(req.params.id);

  if (!id) {
    return res.status(400).json({
      success: false,
      error: "Invalid id",
    });
  }

  const result = await updateDataSetStatusService(
    id,
    StatusEnum.APPROVED
  );

  if (!result.success) {
    return res.status(500).json(result);
  }

  return res.status(200).json(result);
};

export const rejectDataSetController = async (
  req: Request,
  res: Response
) => {
  const id = Number(req.params.id);

  if (!id) {
    return res.status(400).json({
      success: false,
      error: "Invalid id",
    });
  }

  const result = await updateDataSetStatusService(
    id,
    StatusEnum.REJECTED
  );

  if (!result.success) {
    return res.status(500).json(result);
  }

  return res.status(200).json(result);
};
export const viewDataSetController = async (
  req: Request,
  res: Response
) => {
  const id = Number(req.params.id);

  if (!id) {
    return res.status(400).json({
      success: false,
      error: "Invalid id",
    });
  }

  const result = await getDataSetWithCsvService(id);

  if (!result.success) {
    return res.status(404).json(result);
  }

  return res.status(200).json(result);
};