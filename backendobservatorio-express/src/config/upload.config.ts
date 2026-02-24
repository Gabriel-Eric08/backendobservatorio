// src/config/upload.config.ts

import multer from "multer";
import path from "path";
import fs from "fs";

// Garante que a pasta uploads exista
const uploadDir = path.resolve("uploads");

if (!fs.existsSync(uploadDir)) {
  fs.mkdirSync(uploadDir);
}

// Remove acentos, espaços e caracteres especiais
function sanitizeFileName(name: string) {
  return name
    .normalize("NFD")
    .replace(/[\u0300-\u036f]/g, "") // remove acentos
    .replace(/\s+/g, "_") // espaço -> _
    .replace(/[^a-zA-Z0-9_-]/g, ""); // remove especiais
}

const storage = multer.diskStorage({
  destination: (_req: any, _file: any, cb: any) => {
    cb(null, uploadDir);
  },

  filename: (_req: any, file: any, cb: any) => {
    const ext = path.extname(file.originalname).toLowerCase();

    if (ext !== ".csv") {
      return cb(new Error("Only CSV files are allowed"), "");
    }

    const baseName = path.basename(file.originalname, ext);
    const safeName = sanitizeFileName(baseName);

    const timestamp = new Date()
      .toISOString()
      .replace(/[:.]/g, "-");

    const finalName = `${safeName}_${timestamp}.csv`;

    cb(null, finalName);
  },
});

export const upload = multer({
  storage,
  limits: {
    fileSize: 5 * 1024 * 1024, // 5MB
  },
}); 