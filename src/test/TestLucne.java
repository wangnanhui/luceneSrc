package test;

import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FieldDoc;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

public class TestLucne {
	public static void main(String[] args) throws IOException {
		Directory dir = FSDirectory.open(Paths.get("/Users/wangwang/lucene/index/test"));
		IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(dir, conf);
		for (int i = 0; i < 100000; i++) {
			Document doc = new Document();
			StringField field = new StringField("name", new BytesRef(i ), Store.YES);
			doc.add(field);
			writer.addDocument(doc);
		}
		writer.close();
		
		IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get("/Users/wangwang/lucene/index/test"))));
		Query query = new PrefixQuery(new Term("name","test1"));
		TopDocs doc = searcher.search(query, 5);
		ScoreDoc [] docs = doc.scoreDocs;
		FieldDoc fieldDoc = new FieldDoc(docs[docs.length - 1].doc, docs[docs.length - 1].score);
		Object [] obj = new Object[1];
		obj[0]="name";
		fieldDoc.fields =obj ;
		Sort sort = new Sort(SortField.FIELD_SCORE);
	//	searcher.searchAfter(fieldDoc, query,sort,true,true);
	}

}
