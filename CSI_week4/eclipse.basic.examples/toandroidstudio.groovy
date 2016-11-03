import groovy.io.FileType
import java.nio.file.Path
import java.nio.file.Paths


new File(".").eachFile(FileType.DIRECTORIES) { File appDir ->
	if(appDir.isHidden() ) return

	Path basePath = Paths.get(appDir.absolutePath)
	println appDir.name

	def srcDir = new File(appDir.path + "/src")
	def manifest = new File(appDir.path + "/AndroidManifest.xml")
	def resDir = new File(appDir.path + "/res")

	[srcDir,manifest,resDir].each{ File f ->
		Path filePath = Paths.get(f.absolutePath)
		println basePath.relativize(filePath)
		
		return
		def artefact = f.path.split(appDir.name)[-1][1..-1]
		def path = new File("./${appDir.name}/$artefact")
		println "P: " + path.path
	}
}